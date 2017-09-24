define(
    "TodoContainerView", 
    [
        "backbone"
    ], 
    function(Backbone){
        
        return Backbone.View.extend({
            
            el: "#main",
            
            initialize: function(){
                this.render();
                this.section1 = this.$("#section1");
                this.section2 = this.$("#section2");
            },
            
            showSection1: function(view1){
                this.closeSection1();
                this.view1 = view1;
                this.section1.append(this.view1.el);
            },
            
            closeSection1: function(){
                if (this.view1){
                    this.view1.remove();
                }
            },
            
            showSection2: function(view2){
                this.closeSection2();
                this.view2 = view2;
                this.section2.append(this.view2.el);
            },            
            
            closeSection2: function(){
                if (this.view2){
                    this.view2.remove();
                }
            }            
        });
    }
);