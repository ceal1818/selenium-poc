require.config({
	baseUrl: "lib",
	paths: {
		jquery: 'jquery.min',
		underscore: 'underscore-min',
		bootstrap: 'bootstrap.min',
		backbone: 'backbone-min',
        'backbone.localStorage': 'backbone.localStorage.min',
		text: 'text',
        moment: 'moment',
        
        TodoModel: '../js/models/TodoModel',
        TodoCollection: '../js/collections/TodoCollection',
        TodoRouter: '../js/routers/TodoRouter',
        
        TodoBaseFormView: '../js/views/TodoBaseFormView',
        TodoFormView: '../js/views/TodoFormView',
        TodoAdvancedFormView: '../js/views/TodoAdvancedFormView',
        TodoItemView: '../js/views/TodoItemView',
        TodoContainerView: '../js/views/TodoContainerView',
        TodoCollectionView: '../js/views/TodoCollectionView'
	},
	shim: {
		jquery: {
			exports: "$"
		},
		underscore: {
			exports: "_"
		},
		backbone: {
			deps: ["jquery", "underscore"],
			exports: "Backbone"
		},
		bootstrap: {
			exports: "bootstrap"
		},
	}
});

require(
		[
            "backbone",
            "TodoCollection",
			"TodoRouter"
		],
		function (Backbone, TodoCollection, TodoRouter) {
			var todoRouter = new TodoRouter({
                collection: new TodoCollection()
            });
            Backbone.history.start();
		}
);
