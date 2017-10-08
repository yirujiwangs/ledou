(function () {
  'use strict';
  angular
  .module('center')
  .directive('deviceGroupEdit', deviceGroupEdit);

  deviceGroupEdit.$inject = ['DeviceService'];

  function deviceGroupEdit(DeviceService) {
    return {
      scope: {
        groupName: '='
      },
      restrict : 'E',
      replace: true,
      templateUrl: 'scripts/directives/deviceGroupEdit.html',
      link: function (scope,elem,attrs) {
        // isEdit为true则为修改状态
        scope.isEdit = false;
        // 点击小图标修改
        scope.edit = function(){
            scope.isEdit = !scope.isEdit;
        }
        // 点击确定提交修改
        scope.update = function(){
            scope.isEdit = !scope.isEdit;
        }
      }
    }
  }
})()