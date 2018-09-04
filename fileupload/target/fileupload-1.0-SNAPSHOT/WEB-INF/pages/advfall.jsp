<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap/bootstrap.min.css">
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/public/js/arttemplate.js"></script>--%>
<style type="text/css">
   /* body{
        width: 80%;
    }*/
    *{padding: 0; margin: 0}
    .box1{
        width: 40%;
        height: 40%;
        position: fixed;
        right: 0;
        bottom: 0;
        border: 2px solid #d2d6de;
        background-color: #fff;
        font-size: 16px;
        overflow: auto;
    }
    .box2{
        width: 40%;
        position: fixed;
        right: 0;
        bottom: 0;
        border: 2px solid #d2d6de;
        background-color: #fff;
        font-size: 16px;
    }
    .element{
        margin-left: 20px;
    }
    /* #progress{
         float: right;

     }*/

    #example2_wrapper table th, tr {
        font-size: 13px;
        text-align: center;
    }

    #example2_wrapper table th {
        font-size: 14px;
        color: #fff;
        background-color: #222D32;
    }
    #waterfallContainer{
        margin-top:-30px;
    }

    .box-header {
        padding: 18px;
    }

    #theToolTip p {
        font-size: 12px;
        font-family: "Meiryo UI", "Microsoft YaHei", "Malgun Gothic", "Segoe UI", "Trebuchet MS", Helvetica, "Monaco", monospace, Tahoma, STXihei, "华文细黑", STHeiti, "Helvetica Neue", "Droid Sans", "wenquanyi micro hei", FreeSans, Arimo, Arial, SimSun, "宋体", Heiti, "黑体", sans-serif;
        color: #7d7d7d;
    }

    #theToolTip span {
        font-size: 12px;
        font-family: Heiti;
        /* color: #313131;*/
    }

    .thumbnail{
        margin-top:45px;
        width: 245px;
        height: 135px;
    }
    .videobottom{
        margin-top: 115px;
    }
    .videobottom p {
        position: absolute;
        bottom:45px;
    }
    .centerarea{
        margin-top: -15px;
        width: 240px;
    }
    .btn{
        cursor:default ;
    }
    #picker {
        display: inline-block;
        line-height: 1.428571429;
        vertical-align: middle;
        margin: 0 12px 0 0;
    }
    .progress{
        background-color: #299fd1!important;
        width: 10%;
    }
    .close{
        opacity: .5!important;
        margin-left: 10px;
    }
</style>
<body>
<section id="datadiv" class="content" style="padding-top: 5px;">
    <!-- 表单查询 -->
    <div class="box box-success" style="border-top-width: 1px;margin-bottom: 10px;">
        <div class="box-body">
            <form class="form-horizontal">
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <label class="radio-inline">
                                <input type="radio"  name="adv" onclick="find()" value="2">全部
                            </label>
                            <label class="radio-inline">
                                <input type="radio"  name="adv" onclick="find()" value="0">视频
                            </label>
                            <label class="radio-inline">
                                <input type="radio"  name="adv" onclick="find()" value="1">图片
                            </label>
                        </div>

                        <div class="col-md-4">
                            <div class="input-group" style="width: 63%">
                                <input type="text"  id="name" class="form-control" placeholder="请输入搜索文件名称">
                                <div class="input-group-addon" onclick="find();" style="background-color:#299fd1;color: #fff">搜索</div>
                            </div>
                            <%--<input type="text"  id="name"--%>
                            <%--placeholder="请输入搜索文件名称">--%>
                            <%--<button type="button" class="btn btn-primary" onclick="find();">查询</button>--%>

                        </div>

                        <div class="col-md-4 btns">
                            <div id="picker">
                                <%--<button onclick="jQuery('.box1').show()" class="btn btn-default show">开始上传</button>--%>
                                <%--<a href="javascript:;" onclick="jQuery('.box1').show()" class="show" style="color: #fff">上传</a>--%>
                                上传
                            </div>
                            <button type="button" class="btn  btn-default" onclick="deleteadv();" style="padding:9px 15px">删除</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- 白名单列表 -->
    <div class="box" style=" border-top-width: 0px;margin-bottom: 0px;">

        <div id="waterfallContainer" class="box-body" style="padding-bottom: 0px;padding-top: 0px;">
            <%--<div class="row">
                <div class="col-md-12">
                    <a href="#" id="top" style="display:none">
                        <button onclick="backTop()"   style="position:fixed;right:30px;bottom:20px; z-index: 9999;"
                                type="button" class="btn btn-success btn-flat">
                            <i class="fa fa-long-arrow-up"></i>
                        </button>
                    </a>
                </div>
            </div>
            <div>
            </div>--%>
        </div>


    </div>

    <div class="box1" style="display: none">
        <%--  <a href="javascript:;" onclick="jQuery('.box').hide()" class="close">最小化</a>
          <a href="javascript:;" onclick="jQuery('.box').hide()" class="close">关闭</a>--%>
        <a href="javascript:;" id="close1" class="close"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
        <a href="javascript:;" id="min" class="close"> <span class="glyphicon glyphicon-minus" aria-hidden="true"></span></a>

        <div style="padding: 10px">
            <%--<input type="text" id="allfilenum">--%>
            <p><span id="allfilenum"></span></p>
        </div>
        <!--用来存放文件信息-->
        <div id="thelist" class="uploader-list"></div>
    </div>

    <div class="box2">
        <a href="javascript:;" id="close2" class="close"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
        <a href="javascript:;" id="max" class="close"><span class="glyphicon glyphicon-book" aria-hidden="true"></span></a>

    </div>
</section>
<script type="text/x-handlebars-template" id="waterfall-tpl">
    {{#waterfall data}}
    <div class="col-md-3">
        <div class="thumbnail">
            <div class="videobottom">
                <img src={{adv_url}} onerror="nofindimg(event)" height="135px" width="240px" style="margin-top: -130px;margin-left: -5px;">
                <p>
                    <span class="{{adv_type}}" aria-hidden="true"></span>
                    <span style="color: black">{{adv_time}}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span  style="margin-right: 5px">{{adv_resolving_power}}</span>
                    <span  style="color: black">{{adv_size}}</span>
                </p>

            </div>
        </div>
        <div class="centerarea">
            <span class="pull-left">上传日期：{{create_time}} </span>
            <span class="pull-right">   {{file_type}}</span>
        </div>
        <div style="text-align: center;margin-top: 40px">
            <input type="checkbox" name="checkbox" value={{id}}><span id="{{id}}" onclick="updatefilename(this)"  data-fileid="{{id}}">{{file_name}}</span>
        </div>
    </div>
    {{/waterfall}}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery/jQuery-2.2.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/waterfall/waterfall.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/handlebars.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/common/base-modal.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/common/base.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/common/alertinfo.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/alert/sweetalert2.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/js/jquery/jquery.imageload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script>


    function nofindimg(event) {
        var img = event.srcElement||event.target;

        img.src = "${pageContext.request.contextPath}/static/img/spring.png";

        img.onerror = null;
    }


    $(document).ready(function () {
      /*  debugger
        var maxPage=0;
        $.ajax({
            url:"{pageContext.request.contextPath}/screen/getadverlistmaxpage",
            type:'get',
            dataType:'json',
            success:function (data) {
                console.log(data.result+"wwwww")
                debugger
                if(data.result==0){
                    debugger
                    maxPage=data.data;*/
                    $('#waterfallContainer').waterfall({
                        debug: false,
                        path: function (page) {
                            var url="${pageContext.request.contextPath}/screen/adverlist/12/"+page+"?1=1";
                            if($("#name").val() !=null && $("#name").val() !=""){
                                url+="&name="+$("#name").val();
                            }
                            if($("input[name='adv']:checked").val() !=null && $("input[name='adv']:checked").val() !=""){
                                url+="&filetype="+$("input[name='adv']:checked").val();
                            }
                            return url;
                        }
                    });

             /*   }
            }

        });*/

    });


    Handlebars.registerHelper('waterfall', function (result, options) {
        var items = result;
        var out = '';
        for (var i = 0; i < items.length; i++) {
            out += options.fn(items[i])
        }
        return out;
    });

    function find() {
        $('#waterfallContainer').waterfall('removeItems', 'div.col-md-3');
        $('#waterfallContainer').waterfall('scroll');
    }
    //获取class为caname的元素
    function updatefilename (file) {
        var id = file.getAttribute("data-fileid");
        var filename=$("#"+id).text();
        var txt = $.trim(filename);
        var input = $("<input type='text'value='" + txt + "' style='height: 18px;'/>");
        $("#"+id).html(input);
        input.click(function () { return false; });
        //获取焦点
        input.trigger("focus");
        //文本框失去焦点后提交内容，重新变为文本
        input.blur(function () {
            var newtxt = $(this).val();
            //判断文本有没有修改,jaxa请求后台接口
            if (newtxt != txt) {
                //ajax
                $.ajax({
                    url:"${pageContext.request.contextPath}/screen/updatefilename/"+id+"/"+newtxt,
                    type:'get',
                    dataType:'json',
                    success:function (data) {
                        if(data.result==0){
                            $("#"+id).text(newtxt);
                        }
                        if(data.result==1){
                            $.TimeAlert('error',data.msg,'error');
                            $("#"+id).text(txt);
                        }
                    }

                })
            }else {
                $("#"+id).text(newtxt);
            }
        })

    };




    function deleteadv() {
        //删除功能
        debugger
        var len = $("input[name='checkbox']:checked").length;
        var checkBoxVal = $("input[name='checkbox']:checked").val();
        var spCodesTemp = "";
        $("input[name='checkbox']:checked").each(function(i){
            if(0==i){
                spCodesTemp = $(this).val();
            }else{
                spCodesTemp += (","+$(this).val());
            }
        });
        var url="";
        if (len==0) {
            $.TimeAlert('info',"请选择一条您要操作的数据");
          /*  alert('info',"请选择一条您要操作的数据");*/
            return false;
        }
        if(len==1){
            jurl="${pageContext.request.contextPath}/screen/delete?id="+checkBoxVal;
        }else{
            jurl="${pageContext.request.contextPath}/screen/batchdeleteAdv/"+spCodesTemp;
        }
        modals.confirm("<img src='${pageContext.request.contextPath}/static/img/public-caution.png' style='margin-right:10px'>确认删除当前所选广告信息？请谨慎操作！", function () {
            ajaxPost(jurl, null, function (data, status) {
                //无返回值，也刷新页面
                find();
                if (status == "success") {
                    //刷新页面
                    find();
                    if (data.result ==0) {
                        $.TimeAlert('success', data.msg, 'success');
                    } else {
                        $.TimeAlert('error', data.msg, 'error');
                    }
                } else {
                    //刷新页面
                    find();
                    $.TimeAlert('error', '删除失败！', 'error');
                }

            });
        });

    }
</script>
<script type="text/javascript">
    $(document).ready(function(){
        var  allfileNum;
        var  queuedfile=0;
        var  completefile=0;
        $(".box1").hide();
        $(".box2").hide();
        $("#picker").click(function () {
            $(".box2").hide();
        });

        $("#min").click(function () {
            $(".box1").hide();
            $(".box2").show();
        });
        $("#max").click(function () {
            $(".box2").hide();
            $(".box1").show();
        })


        jQuery(function() {
            var $ = jQuery,
                $list = $('#thelist'),
                /*   $btn = $('#ctlBtn'),*/
                $picker = $('#picker'),
                state = 'pending',
                uploadedfilenum,
                uploader;
            /*  $(document).ready(function(){*/
            uploader = WebUploader.create({

                // swf文件路径
                swf: '/static/swf/Uploader.swf',

                // 文件接收服务端。
                server: '/screen/upload',

                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: {
                    id:'#picker',
                    //是否允许单次上传时同时选择多个文件
                    multiple:true
                },
                //[可选] [默认值：undefined] 验证文件总数量, 超出则不允许加入队列。
                fileNumLimit: 20,
                /*pick: '#ctlBtn',*/
                //fileSingleSizeLimit {int} [可选] [默认值：undefined] 验证单个文件大小是否超出限制, 超出则不允许加入队列
                //500M,暂时针对视频
                fileSingleSizeLimit:500*1024*1024,
                method:'POST',
                accept: {
                    title: 'file',
                    extensions: 'jpg,bmp,png,mp4,flv'
                },


                // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                resize: false,
                duplicate: true
            });

            uploader.on('error',function (handler) {
                if(handler=="Q_TYPE_DENIED"){
                    alert("请上传jpg,bmp,png,mp4,flv格式的文件！");
                }
                if(handler=="F_DUPLICATE"){
                    alert("请不要重复选择文件！");
                }
                if(handler=="Q_EXCEED_NUM_LIMIT"){
                    alert("超出最大文件上传个数20");
                }
                if(handler=="F_EXCEED_SIZE"){
                    alert("单个视频大小超过500M");
                }
            })

            // 当有文件被添加进队列的时候
            uploader.on('fileQueued', function (file) {
                $(".box1").show();
                //对文件的一些属性，进行处理
                //file.name
                var arrstr=file.name.split(".");
                var name=arrstr[0];
                var filetype=arrstr[1];
                //file.size
                var size= Math.round(file.size/1024/1024)+"M";
                //上传弹出框文件前的图标(视频)
                if(filetypeInArray(['mp4','flv'],filetype)==true){
                    $list.append('<div id="' + file.id + '">' +
                        '<div class="col-md-5"><span class="glyphicon glyphicon-facetime-video" aria-hidden="true"></span><span class="element">' + (name.substring(0,10)+"...") + '</span></div>' +
                        '<div class="col-md-2"><span class="element">'+file.ext+'</span></div>'+
                        '<div class="col-md-2"><span class="element">'+size+'</span></div>'+
                        '<div class="col-md-3"><p class="state">正在上传</p></div>' +
                        '</div>');
                }

                //图片
                if(filetypeInArray(['jpg','bmp','png'],filetype)==true){
                    $list.append('<div id="' + file.id + '">' +
                        '<div class="col-md-5"><span class="glyphicon glyphicon-picture" aria-hidden="true"></span><span class="element">' + (name.substring(0,10)+"...") + '</span></div>' +
                        '<div class="col-md-2"><span class="element">'+file.ext+'</span></div>'+
                        '<div class="col-md-2"><span class="element">'+size+'</span></div>'+
                        '<div class="col-md-3"><p class="state">正在上传</p></div>' +
                        '</div>');
                }

                queuedfile++;
                allfileNum="正在上传"+completefile+"/"+queuedfile;
                $("#allfilenum").html(allfileNum);
                uploader.upload();
                /* alert("提示成功添加20个上传，5个上传未添加");*/
            });


            // 文件上传过程中创建进度条实时显示。
            uploader.on('uploadProgress', function (file, percentage) {
                $(".state").hide();
                var $li = $('#'+file.id+':last'),
                    $percent = $li.find('.progress .progress-bar');

                // 避免重复创建
                if (!$percent.length) {
                    $percent = $('<div class="col-md-3 progress progress-striped active" style="margin-bottom: 11px;">'+
                        '<div class="progress-bar" role="progressbar" style="width: 0%">'+
                        '</div>'+
                        '</div>').appendTo($li).find('.progress-bar');
                }

//                $li.find('p.state').text('上传中222');
                $percent.css('width', percentage * 100 + '%');

            });

            uploader.on('uploadSuccess', function (file,response) {
                $(".state").show();
                $('#' + file.id).find('p.state').text('上传完成');

                //上传完成后视频列表页面刷新
                find();

            });

            uploader.on('uploadError', function (file) {
                $("[class='col-md-3 progress progress-striped active']").hide();
                $(".state").show();

                $('#' + file.id).find('p.state').text('上传出错');
            });

            uploader.on('uploadComplete', function (file) {
                completefile++;
                allfileNum="正在上传"+completefile+"/"+queuedfile;
                $("#allfilenum").html(allfileNum);

                //弹出小框，大框影藏
                if(completefile==queuedfile&&queuedfile!=0&&completefile!=0){
                    $("#allfilenum").html("上传完成");
                    $(".box1").hide();
                    $(".box2").show();

                }
                $('#' + file.id).find('.progress').fadeOut();
                $(".state").show();
            });

            uploader.on( 'all', function( type ) {
                if ( type === 'startUpload' ) {
                    state = 'uploading';
                } else if ( type === 'stopUpload' ) {
                    state = 'paused';
                } else if ( type === 'uploadFinished' ) {
                    state = 'done';
                }
            });

            $("#close1").click(function () {
                //文件未上传，关闭按钮
                if(queuedfile-completefile>0){
                modals.confirm("<img src='${pageContext.request.contextPath}/static/img/public-caution.png' style='margin-right:10px'>列表中有未上传完成的文件，确定要放弃上传吗？", function () {
                    uploader.stop(true);
                    //上传完成后视频列表页面刷新(延时一点时间以后执行)
                    setTimeout(
                        find(),
                    5000)
                });
                }
                $(".box1").hide();
                //清除上传进度列表box1中数据
                $("#allfilenum").empty();
                $("#thelist").empty();


            });

            $("#close2").click(function () {
                //文件未上传，关闭按钮
                if(queuedfile-completefile>0){
                modals.confirm("<img src='${pageContext.request.contextPath}/static/img/public-caution.png' style='margin-right:10px'>列表中有未上传完成的文件，确定要放弃上传吗？", function () {
                    uploader.stop(true);
                    //上传完成后视频列表页面刷新(延时一点时间以后执行)
                    setTimeout(
                        find(),
                        5000)
                });
                }
                $(".box2").hide();
                //清除上传进度列表box1中数据
                $("#allfilenum").empty();
                $("#thelist").empty();
            });
        });


    })
</script>
<script>
    /**
     * 使用循环的方式判断一个元素是否存在于一个数组中
     * @param {Object} arr 数组
     * @param {Object} value 元素值
     */
    function filetypeInArray(arr,value){
        for(var i = 0; i < arr.length; i++){
            if(value === arr[i]){
                return true;
            }
        }
        return false;
    }
</script>

</body>








