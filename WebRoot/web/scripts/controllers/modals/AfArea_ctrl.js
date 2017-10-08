(function(){
    'use strict';

    angular
        .module('center')
        .controller('AfAreaCtrl', AfAreaCtrl);

    AfAreaCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','$modalInstance','$modal','AlertService','utilService','items'];

    function AfAreaCtrl($scope,$rootScope,$location,$route,$modalInstance,$modal,AlertService,utilService,items){
        //因为区域过多，所以为了不影响性能采用了操作DOM来实现。因为angular的每个双向绑定会产生一个观察者，过多（官方给出超过2000）性能会越来越差！
        var vm = this;
        vm.allCity=allCity;
        //有几个市没有区县地区代码所以选不全
        function showChoosed(){
            if(!items)return;
            var itemsLen=items.length;
            var districts=document.querySelectorAll("input[data='district']");
            var districtLen=districts.length;
            var citys=document.querySelectorAll("input[data='city']");
            var cityLen=citys.length;
            var provinces=document.querySelectorAll("input[data='province']");
            var provinceLen=provinces.length;
            for(var i=0;i<itemsLen;++i){
                for(var j=0;j<districtLen;++j){
                    var districtId=districts[j].parentNode.querySelector("i[data='district']").innerText;
                    if(items[i].Id==districtId){
                        districts[j].checked=true;
                        break;
                    }
                }
                for(var k=0;k<cityLen;++k){
                    var cityId=citys[k].parentNode.querySelector("i[data='city']").innerText;
                    if(items[i].Id==cityId){
                        citys[k].checked=true;
                        break;
                    }
                }
                for(var h=0;h<provinceLen;++h){
                    var provinceId=provinces[h].parentNode.querySelector("i[data='province']").innerText;
                    if(items[i].Id==provinceId){
                        provinces[h].checked=true;
                        break;
                    }else if(items[i].Id=="99999"){
                        provinces[0].checked=true;
                        break;
                    }
                }
            }
            //选完了,强制添加一波
            vm.addAddress();
        }
        //放入消息队列末尾，必须等angular编译（compile）（渲染例如ng-repeat）完毕才能操作DOM
        setTimeout(showChoosed,0);
        //再添加

        vm.addAddress=function(){
            var districts=document.querySelectorAll("input[data='district']");
            var districtLen=districts.length;
            vm.addressChoosed=[];
            for(var i=0;i<districtLen;++i){
                if(districts[i].checked==true){
                    var districtName=districts[i].parentNode.querySelector("span[data='district']").innerText;
                    var districtId=districts[i].parentNode.querySelector("i[data='district']").innerText;
                    var cityName=districts[i].parentNode.parentNode.querySelector("span[data='city']").innerText;
                    //'>i' is not a valid selector.前边必须有它的父节点
                    var cityId=districts[i].parentNode.parentNode.querySelector("i[data='city']").innerText;
                    var provinceName=districts[i].parentNode.parentNode.parentNode.querySelector("span[data='province']").innerText;
                    var provinceId=districts[i].parentNode.parentNode.parentNode.querySelector("i[data='province']").innerText;
                    vm.addressChoosed.push({
                        name:provinceName+cityName+districtName,
                        Id:districtId,
                    });
                }
            }
            //js 两次声明不会编译出错和java不一样
            var citys=document.querySelectorAll("input[data='city']");
            var cityLen=citys.length;
            for(var i=0;i<cityLen;++i){
                if(citys[i].checked==true){
                    var cityName=citys[i].parentNode.querySelector("span[data='city']").innerText;
                    var cityId=citys[i].parentNode.querySelector("i[data='city']").innerText;
                    var provinceName=citys[i].parentNode.parentNode.querySelector("span[data='province']").innerText;
                    var provinceId=citys[i].parentNode.parentNode.querySelector("i[data='province']").innerText;
                    vm.addressChoosed.push({
                        name:provinceName+cityName,
                        Id:cityId,
                    });
                }
            }
            var provinces=document.querySelectorAll("input[data='province']");
            var provinceLen=provinces.length;
            for(var i=0;i<provinceLen;++i){
                if(provinces[i].checked==true) {
                    var provinceName = provinces[i].parentNode.querySelector("span[data='province']").innerText;
                    var provinceId = provinces[i].parentNode.querySelector("i[data='province']").innerText;
                    if (provinceName == '全国')provinceId = "99999";
                    vm.addressChoosed.push({
                        name: provinceName,
                        Id: provinceId,
                    });
                }
            }
        }

        vm.isShow=function(event,what){
            if(what=='city'){
                var list=event.target.parentNode.querySelectorAll("div[data='city']");
            }
            else if(what=='district'){
                var list=event.target.parentNode.querySelectorAll("div[data='district']");
            }
            var len=list.length;
            for(var i=0;i<len;++i){
                if(list[i].style.display=="block"){
                    list[i].style.display="none";
                }
                else if(list[i].style.display=="none"){
                    list[i].style.display="block";
                }
            }
        }

        vm.remove = function(index){
            vm.addressChoosed.splice(index,1);
        }

        vm.ok = function(){
            $modalInstance.close(vm.addressChoosed);
        }

        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()