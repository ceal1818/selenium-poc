define(
    "TodoAdvancedFormView",
    [
        "TodoBaseFormView",
        "underscore",
        "moment",
        "TodoModel",
        "text!../js/views/templates/todo-advanced-form-view.html"
    ], 
    function(TodoBaseFormView, _, moment, TodoModel, todoFormView){
        
        return TodoBaseFormView.extend({
    
            template: _.template(todoFormView),

            events: {
                "click #update": "_onUpdate",
                "click #cancel": "_onClear"
            },
    
            initialize: function(options){
                this.router = options.router;
                this.render();
                this._onLoad();
            },

            _onUpdate: function(){
                this.model.set({
                    title: this.title.val(),
                    description: this.description.val(),
                    complete: this.complete.is(":checked"),
                    modifiedDate: moment(new Date())
                        .format("YYYY-MM-DD hh:mm:ss.SSS"),
                    dueDate: this.dueDate.val(),
                    guests: this.guests.val()
                });
                
                if (this.model.isValid()){
                    this.model.save();
                    this._onClear();                    
                }
                else{
                    var that = this;
                    this.errorsContainer.removeClass("hidden");
                    _.each(this.model.validationError, function(error){
                        that.errorsList.append("<li>"+error+"</li>");
                    });                    
                }
            },

            _onLoad: function(){
                this.title.val(this.model.get("title"));
                this.description.val(this.model.get("description"));
                this.complete.val(this.model.get("complete"));
                
                this.dueDate = this.$("#dueDate");
                this.guests = this.$("#guests");
                
                if (this.model.has("guests")){
                    this.dueDate.val(this.model.get("dueDate"));
                }
                
                if (this.model.has("guests")){
                    this.guests.val(this.model.get("guests")); 
                }
            },
            
            _onClear: function(){
                this.router.navigate("", true);
            }

        });
    }
);