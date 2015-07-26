var MainLayer = cc.Layer.extend({
	sprite:null,
	ctor:function () {
		this._super();
		this.addBackground();
		this.addMenu();
	},
	addBackground:function(){

	},
	addMenu:function(){

	}
});

var MainScene = cc.Scene.extend({
	onEnter:function () {
		this._super();
		var layer = new MainLayer();
		this.addChild(layer);
	}
});