/*
Visual composition framework using sketch Class

Aris Bezas Fri, 19 November 2010, 19:34 PM
*/

(
~sketchComposition = [
	SyncAction(\beats, { | beat |
		beat.postln;
		switch (beat,
			0, {	
				// INITIALIZE
				PoscDef.load;
				Pdef(\sketchPat).play;
				PoscDef.message.source = 
					Ptuple(
						[	
							Ptuple(['rgb', 'r8', Pseq([0,255,100], inf)] , inf),
							Ptuple(['rgb', 'g8', Pseq([0,255,100], inf)] , inf),
							Ptuple(['rgb', 'b8', Pseq([0,255,100], inf)] , inf),							Ptuple(['rgb', 'a8', Pseq([30,30,30], inf)] , inf)
						], inf) ;
				PoscDef.duration.source = Pseq([0.2],inf);				OF.interactWithSound("activate", 1);
				OF.interactWithSound("maxSoundElasticity",0.1);
				OF.interactWithSound("glBeginType", 1);
				OF.interactWithSound("numSoundSketches", 800);				OF.rgb("background", 0,0,0,120);						OF.rgb("sound", 255,255,255,255);				}		
		);	
		if (beat > 120 and:  { beat <  288;} and: { beat % 1 == 0;}) {  };	})
];
)
~beatPhrase do: _.deactivate;
~beatPhrase do: _.activate;

~sketchCompositionSender = SyncSender(Pbind.new);
~sketchCompositionSender.start;
~sketchCompositionSender.stop;

/*
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
