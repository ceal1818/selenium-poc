define(
    "TodoCollectionView",
    [
        "backbone",
        "underscore",
        "TodoItemView",
        "text!../js/views/templates/todo-collection-view.html"
    ], 
    function(Backbone, _, TodoItemView, todoCollectionView){
        return Backbone.View.extend({

            tagName: "table",
            
            className: "table table-striped",
            
            template: _.template(todoCollectionView),

            initialize: function(options){
                this.formView = options.formView;
                
                this.listenTo(this.formView, "todo:created", this._addTodo);
                
                this.render();
            },

            render: function(event){
                var that = this, todos = null;
                this.$el.html(this.template());
                this.results = this.$("#todo-list");
                this.collection.fetch();
                this._clearItems();
                
                todos = this.collection.where({
                    complete: false
                });
                
                todos.forEach(function(model){
                    that._addTodo(model);
                });
            },

            _addTodo: function(model){
                var todoItemView = new TodoItemView({
                    model: model
                });
                this.items.push(todoItemView);
                
                this.listenTo(todoItemView, "todo:removed", this.render);
                
                this.formView.listenTo(todoItemView, "todo:edit", this.formView._onClearErrors);                
                this.formView.listenTo(todoItemView, "todo:edit", this.formView._onLoad);
                
                this.formView.listenTo(model, "change", this.formView._onClear);
                this.formView.listenTo(model, "change", this.formView._onClearErrors);
                
                this.results.append(todoItemView.el);
            },
            
            _clearItems: function(){
                if (this.items){
                    var that = this;
                    _.each(this.items, function(item){
                        that.formView.stopListening(item);
                        that.stopListening(item);
                        item.remove();
                    });
                }
                this.items = [];
            }
        });        
    }
);