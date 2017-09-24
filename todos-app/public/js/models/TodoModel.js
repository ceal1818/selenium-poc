define(
    "TodoModel", 
    [
        "backbone",
        "backbone.localStorage",
        "underscore"
    ], 
    function(Backbone, BackboneLocalStorage, _){
        return Backbone.Model.extend({
    
            defaults:{
                title: "",
                description: "",
                complete: false,
                createdDate: null,
                modifiedDate: null
            },

            localStorage: new BackboneLocalStorage.LocalStorage("todo"),
            
            validate: function(attrs){
                var validationError = [];
                
                if (_.has(attrs, "title") && _.isEmpty(attrs.title)){
                    validationError.push("The title shouldn't be empty.");
                }
                
                if (_.has(attrs, "description") && _.isEmpty(attrs.description)){
                    validationError.push("The description shouldn't be empty.");
                }                
                
                return (validationError.length > 0) ? validationError: null;
            }
            
        });
    }
);
