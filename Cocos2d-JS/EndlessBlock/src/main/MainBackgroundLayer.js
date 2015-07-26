
var MainBackgroundLayer = cc.Layer.extend({

	_sptBg     : null,
	_sptLogo   : null,
	_ship      : null,
	ctor : function(){
		this._super()

		this.initBackground();

		this.initLogo();
	},

	initBackground : function(){
		this._sptBg = new cc.Sprite(res.back_jpg);
		this._sptBg.attr({
			x: 0,
			y: 0
		});
		this.addChild(this._sptBg);
	},
	
	initLogo : function(){
		this._sptLogo = new cc.Sprite(res.logo_png);
		this._sptLogo.attr({
			x : 180,
			y : 180
		});
		this.addChild(this._sptLogo, 1);
	},
});