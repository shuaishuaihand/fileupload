;(function ($) {
    $.extend({
            MyAlert: function (contrID,val,type,title,Turl) {
                switch (contrID) {
                    case "success":
                        swal({
                            title:title,
                            text: val,
                            type: 'success',
                            confirmButtonText: '确定'
                        });
                        break;
                    case 'error':
                        swal({
                            title: title,
                            text: val,
                            type: 'error',
                            confirmButtonText: '确定'
                        });
                        break;
                    case 'info':
                        swal({
                            //title: "提示",
                            text: val,
                            type: "warning",
                            confirmButtonText: "确认"
                        });
                        break;
                    case 'time':
                        swal({
                            //title: "提示",
                            timer: 2000,
                            text: val,
                            type: type,
                            confirmButtonText: "确认"
                        });
                        break;
                    case "pwd":
                        swal({
                            //title: '提示',
                            text:val,
                            type: 'success',
                            confirmButtonText: "确认",
                        }).then(function (){
                            window.location.href=Turl
                        })
                }
            },
            TimeAlert:function(contrID,val,type,title,Turl){
                switch (contrID) {
                    case "info":
                        swal({
                            //title:title,
                            text: val,
                            showConfirmButton: false,
                            timer: 2000,
                            height:20,
                            width:380
                        });
                        break;
                    case "success":
                        swal({
                            //title:title,
                            text: val,
                            type: 'success',
                            showConfirmButton: false,
                            timer: 2000,
                            height:30,
                            width:380
                        });
                        break;
                    case "error":
                        swal({
                            //title: title,
                            text: val,
                            type: 'error',
                            showConfirmButton: false,
                            timer: 2000,
                            height:30,
                            width:380
                        });
                        break;
                    case 'time':
                        swal({
                            //title: "提示",
                            //timer: 2000,
                            text: val,
                            type: type,
                            confirmButtonText: "确认"
                        });
                        break;
                    case "pwd":
                        swal({
                            //title: '提示',
                            text:val,
                            type: 'success',
                        }).then(function (){
                            window.location.href=Turl
                        })
                }
            }
        }
    );
})(jQuery)