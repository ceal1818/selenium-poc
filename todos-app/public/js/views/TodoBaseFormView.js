define(
    "TodoBaseFormView",
    [
        "backbone",
        "underscore",
        "moment"
    ], 
    function(Backbone, _, moment){
        
        return Backbone.View.extend({
    
            tagName: "form",
            
            attributes: {
                action: "#",
                method: "post",
                novalidate: ""
            },
            
            render: function(){
                this.$el.html(this.template());
                this.title = this.$("#title");
                this.description = this.$("#description");
                this.complete = this.$("#complete");
                this.update = this.$("#update");
                this.errorsContainer = this.$("#errors")
                this.errorsList = this.$("#errors ul")
            },
            
            _onClearErrors: function(){
                this.errorsList.html("");
                this.errorsContainer.addClass("hidden");
            }            
            
        });
    }
);