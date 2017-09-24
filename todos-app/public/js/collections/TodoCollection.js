define(
    "TodoCollection", 
    [
        "backbone",
        "backbone.localStorage",
        "TodoModel"
    ], 
    function(Backbone, BackboneLocalStorage, TodoModel){
        return Backbone.Collection.extend({
    
            model: TodoModel,
            
            localStorage: new BackboneLocalStorage.LocalStorage("todo"),

        });
    }
);