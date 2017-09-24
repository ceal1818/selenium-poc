define(
    "TodoItemView", 
    [
        "backbone",
        "underscore",
        "moment",
        "text!../js/views/templates/todo-item-view.html"
    ], 
    function(Backbone, _, moment,todoItemTemplate){
        return Backbone.View.extend({
    
            tagName: "tr",

            template: _.template(todoItemTemplate),

            events:{
                "click #editBtn": "_onEdit",
                "click #completeBtn": "_onComplete",
                "click #removeBtn": "_onRemove"
            },

            initialize: function(){
                this.listenTo(this.model, "change", this.render);
                this.render();
            },

            render: function(){
                if (this.model){
                    var _model = _.clone(this.model.toJSON());
                    
                    _model.createdDate = moment(_model.createdDate).format("MM-DD-YYYY hh:mm:ss");
                    _model.modifiedDate = (_model.modifiedDate) ? 
                        moment(_model.modifiedDate).format("MM-DD-YYYY hh:mm:ss") : '';
                    
                    this.$el.html(this.template(_model));
                }
            },

            _onEdit: function(){
                this.trigger("todo:edit", this.model);
            },
            
            _onComplete: function(){
                this.model.set({
                    complete: true
                });
                this.model.save();
                this.remove();
            },
            
            _onRemove: function(){
                this.model.destroy();
                this.trigger("todo:removed");
                this.undelegateEvents();
                this.stopListening();
            }            

        });        
    }
);
