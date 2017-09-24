define(
    "TodoFormView",
    [
        "TodoBaseFormView",
        "underscore",
        "moment",
        "TodoModel",
        "text!../js/views/templates/todo-form-view.html"
    ], 
    function(TodoBaseFormView, _, moment, TodoModel, todoFormView){
        
        return TodoBaseFormView.extend({
    
            template: _.template(todoFormView),

            events: {
                "click #create": "_onCreate",
                "click #update": "_onUpdate",
                "click #advanced": "_onAdvanced",
                "click #cancel": "_onClear"
            },
    
            initialize: function(options){
                this.router = options.router;
                this.render();
                this.create = this.$("#create");
                this.advanced = this.$("#advanced");
            },
    
            _onCreate: function(event){
                event.preventDefault();
                var model = new TodoModel({
                    title: this.title.val(),
                    description: this.description.val(),
                    complete: this.complete.is(":checked"),
                    createdDate: moment(new Date())
                        .format("YYYY-MM-DD hh:mm:ss.SSS")
                }), that = this;
                
                if (model.isValid()){
                    this.collection.create(model);

                    var results = this.collection.where({
                        title: this.title.val(),
                        createdDate: model.get("createdDate")
                    });

                    this.trigger("todo:created", results[0]);
                    this._onClear();                    
                }
                else{
                    this.errorsContainer.removeClass("hidden");
                    _.each(model.validationError, function(error){
                        that.errorsList.append("<li>"+error+"</li>");
                    });
                }
            },

            _onUpdate: function(event){
                event.preventDefault();
                var modifiedDate = moment(new Date())
                    .format("YYYY-MM-DD hh:mm:ss.SSS");
                
                if (this.modelId){
                    var model = this.collection.get(this.modelId);
                    model.set({
                        title: this.title.val(),
                        description: this.description.val(),
                        complete: this.complete.is(":checked"), 
                        modifiedDate: modifiedDate
                    });
                    model.save();
                    this._onClear();
                }
            },

            _onLoad: function(todo){
                this.modelId = todo.id;
                this.title.val(todo.get("title"));
                this.description.val(todo.get("description"));
                this.complete.val(todo.get("complete"));
                
                this.create.addClass("hidden");
                this.update.removeClass("hidden");
                this.advanced.removeClass("hidden");
            },
            
            _onAdvanced: function(event){
                event.preventDefault();
                this.router.navigate("advanced/"+this.modelId, true);  
            },
            
            _onClear: function(){
                this.modelId = null;
                this.title.val("");
                this.description.val("");
                this.complete.val(false);
                
                this.create.removeClass("hidden");
                this.update.addClass("hidden");
                this.advanced.addClass("hidden"); 
            },
            
        });
    }
);