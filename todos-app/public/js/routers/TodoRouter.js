define(
    "TodoRouter", 
    [
        "backbone",
        "TodoContainerView",
        "TodoFormView",
        "TodoAdvancedFormView",
        "TodoCollectionView"
    ], 
    function(Backbone, TodoContainerView, TodoFormView, TodoAdvancedFormView, TodoCollectionView){
        
        return Backbone.Router.extend({
            
            routes: {
                "": "_loadMainView",
                "advanced/:id": "_loadAdvancedView"
            },
            
            initialize: function(options){
                this.collection = options.collection;
                this.container = new TodoContainerView();
            },
            
            _loadMainView: function(){
                var formView = new TodoFormView({
                    collection: this.collection,
                    router: this
                });
                
                this.container.showSection1(formView);
                this.container.showSection2(new TodoCollectionView({
                    formView: formView,
                    collection: this.collection
                }));
            },
            
            _loadAdvancedView: function(id){
                var formView = new TodoAdvancedFormView({
                    model: this.collection.get(id),
                    router: this
                });
                
                this.container.showSection1(formView);
                this.container.closeSection2();
            }            
            
        });
    }
);