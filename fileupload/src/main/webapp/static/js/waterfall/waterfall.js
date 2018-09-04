;(function ($, window, document, undefined) {
    'use strict';
    var $window = $(window),
        pluginName = 'waterfall',
        defaults = {
            prefix: 'waterfall', // the waterfall elements prefix
            isFadeIn: false, // fadein effect on loading
            isAnimated: false, // triggers animate when browser window is resized
            animationOptions: { // animation options
            },
            isAutoPrefill: true,  // When the document is smaller than the window, load data until the document is larger
            checkImagesLoaded: true, // triggers layout when images loaded. Suggest false
            path: undefined, // Either parts of a URL as an array (e.g. ["/popular/page/", "/"] => "/popular/page/1/" or a function that takes in the page number and returns a URL(e.g. function(page) { return '/populr/page/' + page; } => "/popular/page/1/")
            dataType: 'json', // json, jsonp, html
            params: {}, // params,{type: "popular", tags: "travel", format: "json"} => "type=popular&tags=travel&format=json"
            headers: {}, // headers variable that gets passed to jQuery.ajax()
            loadingMsg: '<div style="text-align:center;padding:10px 0; color:#999;"><img src="data:image/gif;base64,R0lGODlhEAALAPQAAP///zMzM+Li4tra2u7u7jk5OTMzM1hYWJubm4CAgMjIyE9PT29vb6KiooODg8vLy1JSUjc3N3Jycuvr6+Dg4Pb29mBgYOPj4/X19cXFxbOzs9XV1fHx8TMzMzMzMzMzMyH5BAkLAAAAIf4aQ3JlYXRlZCB3aXRoIGFqYXhsb2FkLmluZm8AIf8LTkVUU0NBUEUyLjADAQAAACwAAAAAEAALAAAFLSAgjmRpnqSgCuLKAq5AEIM4zDVw03ve27ifDgfkEYe04kDIDC5zrtYKRa2WQgAh+QQJCwAAACwAAAAAEAALAAAFJGBhGAVgnqhpHIeRvsDawqns0qeN5+y967tYLyicBYE7EYkYAgAh+QQJCwAAACwAAAAAEAALAAAFNiAgjothLOOIJAkiGgxjpGKiKMkbz7SN6zIawJcDwIK9W/HISxGBzdHTuBNOmcJVCyoUlk7CEAAh+QQJCwAAACwAAAAAEAALAAAFNSAgjqQIRRFUAo3jNGIkSdHqPI8Tz3V55zuaDacDyIQ+YrBH+hWPzJFzOQQaeavWi7oqnVIhACH5BAkLAAAALAAAAAAQAAsAAAUyICCOZGme1rJY5kRRk7hI0mJSVUXJtF3iOl7tltsBZsNfUegjAY3I5sgFY55KqdX1GgIAIfkECQsAAAAsAAAAABAACwAABTcgII5kaZ4kcV2EqLJipmnZhWGXaOOitm2aXQ4g7P2Ct2ER4AMul00kj5g0Al8tADY2y6C+4FIIACH5BAkLAAAALAAAAAAQAAsAAAUvICCOZGme5ERRk6iy7qpyHCVStA3gNa/7txxwlwv2isSacYUc+l4tADQGQ1mvpBAAIfkECQsAAAAsAAAAABAACwAABS8gII5kaZ7kRFGTqLLuqnIcJVK0DeA1r/u3HHCXC/aKxJpxhRz6Xi0ANAZDWa+kEAA7" alt=""><br />Loading...</div>', // loading html
            state: {
                isDuringAjax: false,
                isProcessingData: false,
                curPage: 1 // cur page
            },
            callbacks: {
                loadingStart: function ($loading) {
                    $loading.show();
                    //console.log('loading', 'start');
                },

                loadingFinished: function ($loading, isBeyondMaxPage) {
                    if (!isBeyondMaxPage) {
                        $loading.fadeOut();
                    } else {
                        $loading.remove();
                    }
                },

                loadingError: function ($message, xhr) {
                    $message.html('暂无数据');
                },

                renderData: function (data, dataType) {
                    var tpl,
                        template;

                    if (dataType === 'json' || dataType === 'jsonp') { // json or jsonp format
                        tpl = $('#waterfall-tpl').html();
                        template = Handlebars.compile(tpl);
                        return template(data);
                    } else { // html format
                        return data;
                    }
                }
            },
            debug: false
        };

    function Waterfall(element, options) {
        this.$element = $(element);
        this.options = $.extend(true, {}, defaults, options);
        this._init();
    }


    Waterfall.prototype = {
        constructor: Waterfall,
        _debug: function () {
            if (true !== this.options.debug) {
                return;
            }

            if (typeof console !== 'undefined' && typeof console.log === 'function') {
                if ((Array.prototype.slice.call(arguments)).length === 1 && typeof Array.prototype.slice.call(arguments)[0] === 'string') {
                    console.log((Array.prototype.slice.call(arguments)).toString());
                } else {
                    console.log(Array.prototype.slice.call(arguments));
                }
            } else if (!Function.prototype.bind && typeof console !== 'undefined' && typeof console.log === 'object') {
                Function.prototype.call.call(console.log, console, Array.prototype.slice.call(arguments));
            }
        },


        _init: function (callback) {
            var options = this.options,
                path = options.path;

            this._initContainer();

            if (!path) {
                this._debug('Invalid path');
                return;
            }

            // auto prefill
            if (options.isAutoPrefill) {
                this._prefill();
            }

            // bind scroll
            this._doScroll();
        },

        /*
         * init waterfall container
         */
        _initContainer: function () {
            var options = this.options,
                prefix = options.prefix;

            // $('body').css({
            $window.css({overflow: 'auto'});
            this.$element.after('<div id="' + prefix + '-loading">' + options.loadingMsg + '</div><div id="' + prefix + '-message" style="text-align:center;color:#999;"></div>');
            this.$loading = $('#' + prefix + '-loading');
            this.$message = $('#' + prefix + '-message');
        },


        /*
         * prepend
         * @param {Object} $content
         * @param {Function} callback
         */
        prepend: function ($content, callback) {
            this.$element.prepend($content);
        },

        append: function ($content, callback) {
            this.$element.append($content);
        },

        removeItems: function ($items, callback) {
            this.$element.find($items).remove();
        },

        option: function (opts, callback) {
            if ($.isPlainObject(opts)) {
                this.options = $.extend(true, this.options, opts);

                if (typeof callback === 'function') {
                    callback();
                }
                this._init();
            }
        },


        _requestData: function (callback) {
            var self = this,
                options = this.options,
                maxPage = options.maxPage,
                curPage = options.state.curPage++, // cur page
                path = options.path,
                dataType = options.dataType,
                params = options.params,
                headers = options.headers,
                pageurl;

            if (maxPage !== undefined && curPage > maxPage) {
                options.state.isBeyondMaxPage = true;
                options.callbacks.loadingFinished(this.$loading, options.state.isBeyondMaxPage);
                return;
            }

            // get ajax url
            pageurl = (typeof path === 'function') ? path(curPage) : path.join(curPage);

            this._debug('heading into ajax', pageurl + $.param(params));

            // loading start
            options.callbacks.loadingStart(this.$loading);

            // update state status
            options.state.isDuringAjax = true;
            options.state.isProcessingData = true;

            // ajax
            $.ajax({
                url: pageurl,
                data: params,
                headers: headers,
                dataType: dataType,
                success: function (data) {
                    $('div[name=notData]').remove();
                    if (data.data.length===0)
                    {
                        $('#waterfallContainer').append("<div name='notData' style='position:absolute;bottom:50px;left:50%;margin-left:100px;'>暂无数据</div>");
                    }
                    self._handleResponse(data, callback);
                    self.options.state.isProcessingData = false;
                    self.options.state.isDuringAjax = false;
                },
                error: function (jqXHR) {
                    self._responeseError('error');
                }
            });
        },


        _handleResponse: function (data, callback) {
            var self = this,
                options = this.options,
                content = $.trim(options.callbacks.renderData(data, options.dataType)),
                $content = $(content),
                checkImagesLoaded = options.checkImagesLoaded;

            if (!checkImagesLoaded) {
                self.append($content, callback);
                self.options.callbacks.loadingFinished(self.$loading, self.options.state.isBeyondMaxPage);
            } else {
                $content.imagesLoaded(function () {
                    self.append($content, callback);
                    self.options.callbacks.loadingFinished(self.$loading, self.options.state.isBeyondMaxPage);
                });
            }


        },

        _responeseError: function (xhr) {
            this.$loading.hide();
            this.options.callbacks.loadingError(this.$message, xhr);

            if (xhr !== 'end' && xhr !== 'error') {
                xhr = 'unknown';
            }
            this._debug('Error', xhr);
        },


        _nearbottom: function () {
            return $(document).scrollTop() >= $(document).height() - $(window).height();
        },

        _prefill: function () {
            if (this.$element.height() <= $window.height()) {
                this._scroll();
            }
        },

        _scroll: function () {
            var options = this.options,
                state = options.state,
                self = this;
            if (state.isProcessingData || state.isDuringAjax) {
                return;
            }
            if (this.options.state.curPage ==1) {
                this._requestData(function () {
                    var timer = setTimeout(function () {
                        self._scroll();
                    }, 100);
                });
            }else if(this._nearbottom()){
                this._requestData(function () {
                    var timer = setTimeout(function () {
                        self._scroll();
                    }, 100);
                });
            }else{
                return ;
            }


            //||

        },

        scroll: function () {
            this.options.state.curPage = 1;//页码重置
            var options = this.options,
                state = options.state,
                self = this;
            if (state.isProcessingData || state.isDuringAjax) {
                return;
            }
            this._requestData(function () {
                var timer = setTimeout(function () {
                    self.scroll();
                }, 100);
            });
        },


        _doScroll: function () {
            var self = this,
                scrollTimer;
            $window.bind('scroll', function () {
                if (scrollTimer) {
                    clearTimeout(scrollTimer);
                }

                scrollTimer = setTimeout(function () {
                    //self._debug('event', 'scrolling ...');
                    self._scroll();
                }, 100);
            });
        }

    };


    $.fn[pluginName] = function (options) {
        if (typeof options === 'string') { // plugin method
            var args = Array.prototype.slice.call(arguments, 1);

            this.each(function () {
                var instance = $.data(this, 'plugin_' + pluginName);

                if (!instance) {
                    instance._debug('instance is not initialization');
                    return;
                }

                if (!$.isFunction(instance[options]) || options.charAt(0) === '_') { //
                    instance._debug('no such method "' + options + '"');
                    return;
                }

                //  apply method
                instance[options].apply(instance, args);
            });
        } else { // new plugin
            this.each(function () {
                if (!$.data(this, 'plugin_' + pluginName)) {
                    $.data(this, 'plugin_' + pluginName, new Waterfall(this, options));
                }
            });
        }

        return this;
    };

}(jQuery, window, document));