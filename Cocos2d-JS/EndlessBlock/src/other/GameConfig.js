/**
 * Created by loki on 2015-03-18.
 */

var GC = GC || {};

GC.high_score = [];
GC.total_score = 0;
GC.move_minus_score = 1;
GC.get_score = function(n){
	if( n == 1 ){
		return -10;
	}
	return 2*(n-2);
}
GC.level = 0;

GC.level_1_color=[
                  cc.color(102,204,204),
                  cc.color(204,255,102),
                  cc.color(255,153,204),
                  cc.color(102,102,153),
                  cc.color(255,153,0),
                  cc.color(255,0,51),
                  cc.color(102,102,51),
                  cc.color(0,153,51),
                  cc.color(102,102,102)
];