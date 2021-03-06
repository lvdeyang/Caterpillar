/**
 * Created by lzp on 2019/5/29.
 */
define([
	'text!' + window.APPPATH + 'business-show/page-business-show.html',
	'config',
	'restfull',
	'jquery',
	'context',
	'commons',
	'vue',
	'element-ui',
	'mi-frame',
	'mi-sub-title',
	'mi-user-dialog',
	'css!' + window.APPPATH + 'business-show/page-business-show.css'
], function(tpl, config, ajax, $, context, commons, Vue) {

	var pageId = 'page-business-show';

	var init = function() {

		//设置标题
		commons.setTitle(pageId);

		var $page = document.getElementById(pageId);
		$page.innerHTML = tpl;

		new Vue({
			el : '#' + pageId + '-wrapper',
			data : {
				menus : context.getProp('menus'),
				user : context.getProp('user'),
				groups : context.getProp('groups'),
				activeId : window.BASEPATH + 'index#/page-business-show',
				table : {
					data:[],
					rows : [],
					page:{
                        currentPage:1,
                        sizes:[20, 50, 100, 500, 1000],
                        size:20,
                        total:0
                    }
				},
				dialog : {
					
				}
			},
			methods : {
				rowKey : function(row) {
					return 'live-' + row.id;
				},
				load : function(currentPage) {
					var self = this;
					self.table.rows.splice(0, self.table.rows.length);
					ajax.post('/show/live/list',{
                        currentPage:self.table.page.currentPage,
                        pageSize:self.table.page.size
                    },function(data) {
						var total = data.total;
						var rows = data.rows;
						
						if (rows && rows.length > 0) {
							for (var i = 0; i < rows.length; i++) {
								self.table.rows.push(rows[i]);
							}
							self.table.total = total;
						}
						self.table.currentPage = currentPage;
					});
				},
				handleRecord : function(scope) {
					var self = this;
					var row = scope.row;
					ajax.post('/show/live/startRecord/'+row.id, null,function(data, status) {
						if(status !== 200) return;
                        for(var i=0; i<self.table.rows.length; i++){
                            if(self.table.rows[i].id === data.id){
                                self.table.rows.splice(i, 1, data);
                                break;
                            }
                        }
					}, null, ajax.NO_ERROR_CATCH_CODE);
				},
				handleStopRecord : function(scope) {
					var self = this;
					var row = scope.row;
					ajax.post('/show/live/stopRecord/'+row.id, null,function(data, status) {
						if(status !== 200) return;
                        for(var i=0; i<self.table.rows.length; i++){
                            if(self.table.rows[i].id === data.id){
                                self.table.rows.splice(i, 1, data);
                                break;
                            }
                        }
					}, null, ajax.NO_ERROR_CATCH_CODE);
				},
				handleSizeChange : function(size) {
					var self = this;
					self.table.pageSize = size;
					self.load(self.table.currentPage);
				},
				handleCurrentChange : function(currentPage) {
					var self = this;
					self.load(currentPage);
				}
			},
			created : function() {
				var self = this;
				self.load(1);
			//self.loadCompany();
			}
		});
	};

	var destroy = function() {};

	var groupList = {
		path : '/' + pageId,
		component : {
			template : '<div id="' + pageId + '" class="page-wrapper"></div>'
		},
		init : init,
		destroy : destroy
	};

	return groupList;
});