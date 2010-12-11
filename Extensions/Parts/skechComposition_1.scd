/*
Visual composition framework using sketch Class

menu
OF.interactWithSound("maxSoundElasticity", 0.1);
OF.interactWithSound("glBeginType", 0);
OF.interactWithSound("numSoundSketches", 1);

Aris Bezas Fri, 19 November 2010, 19:34 PM
*/

(
~sketchComposition = [
SyncAction(\beats, { | beat |
	("We are in beat: "+beat).postln;
	switch (beat,
		0, {	
			// INITIALIZE
			PoscDef.load;
			Pdef(\myPat0).play;
			PoscDef.msg0.source = 									Ptuple([												Ptuple(['empty'] , inf)							], inf) ;
			PoscDef.dur0.source = Pseq([1],inf);
			OF.interactWithSound("activate", 1);
			OF.interactWithSound("maxSoundElasticity",0.99);
			OF.interactWithSound("glBeginType", 1);
			OF.interactWithSound("numSoundSketches", 990);			OF.rgb("background", 0,0,0,255);						OF.rgb("sound", 255,255,255,205);				},
		10, {
			
		}	
	);	
	if (beat > 0 and:  { beat <  288;} and: { beat % 1 == 0;}) {  };
	//if (beat % 2 == 0) { OF.interactWithSound("maxSoundElasticity", 1.rand); };
	
	})
];
)
~sketchCompositionSender = SyncSender(Pbind.new);
~sketchCompositionSender.start;
~sketchCompositionSender.stop;

~beatPhrase do: _.deactivate;
~beatPhrase do: _.activate;

/*
##### LIVE CODING #####
PoscDef.msg0.source = 									Ptuple([												Ptuple(['interactWithSound', 'maxSoundElasticity', Pseq([0.1, 1], inf)] , inf),		Ptuple(['interactWithSound', 'numSoundSketches', Pseq([100, 699], inf)] , inf),		Ptuple(['interactWithSound', 'glBeginType', Pseq([0, 0], inf)] , inf)	], inf) ;
PoscDef.dur0.source = Pseq([2],inf);
Pdef(\myPat0).play;
Pdef(\myPat0).stop;

//##########	Testing	##########
~oFaddr = NetAddr("127.0.0.1", nil);
OSCresponder.new(~oFaddr, nil, { arg time, resp, msg; 5.postln} ).add; 


PoscDef.message.source = 
Ptuple(
	[	
		Ptuple(['rgb', 'r8', Pseq([10,100,1], inf)] , inf),
		Ptuple(['rgb', 'g8', Pseq([20,200,2], inf)] , inf),
		Ptuple(['rgb', 'a8', Pseq([30,300,3], inf)] , inf)
	], inf) ;


*/

//#####	Useful Messages	#####
PoscDef.message.source = Ptuple(["interactWithSound", "maxSoundElasticity", Pseq([1, 0.1], inf)], inf);
PoscDef.message.source = Ptuple(["interactWithSound", "numSoundSketches", Pseq([999, 1], inf)], inf);
PoscDef.message.source = Ptuple(["interactWithSound", "glBeginType", Pseq([1, 0, 1, 0, 1, 0, 1, 0], inf)], inf);
PoscDef.message.source = Ptuple(["rgb", "sound", Pseq([255, 255, 255], 1), Pseq([0, 225, 255], 1), Pseq([0, 0, 255], 1), Pseq([15, 15, 255], 1)], 1)
