/*
TransBeing composition framework using sketch Class and iperatou characters

menu
OF.interactWithSound("maxSoundElasticity", 0.5);
OF.interactWithSound("glBeginType", 0);
OF.interactWithSound("numSoundSketches", 10);

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
			Pdef(\myPat0).play;
			PoscDef.msg0.source = 									Ptuple([												Ptuple(['empty'] , inf)							], inf) ;
			PoscDef.dur0.source = Pseq([1],inf);
			OF.interactWithSound("activate", 1);
			OF.interactWithSound("maxSoundElasticity",0.1);
			OF.interactWithSound("glBeginType", 1);
			OF.interactWithSound("numSoundSketches", 1);			OF.rgb("background", 0,0,0,30);						OF.rgb("sound", 255,255,255,255);				},
		10, {
			
		}	
	);	
	if (beat > 0 and:  { beat <  288;} and: { beat % 1 == 0;}) {  };
	//if (beat % 10 == 0;) { OF.interactWithSound("maxSoundElasticity", 1.rand); };
	
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
~type = OSCresponder.new(nil, "sched", { arg time, resp, msg; OF.interactWithSound("glBeginType",(0..1).choose);} ).add; 
~numbers = OSCresponder.new(nil, "sched", { arg time, resp, msg; OF.interactWithSound("numSoundSketches",(1.999).choose);} ).add; 
~iperatou = OSCresponder.new(nil, "sched", { arg time, resp, msg; OF.background(0,0,0);OF.effect("iperatou","shoot");} ).add;
~iperatouSmall = OSCresponder.new(nil, "sched", { arg time, resp, msg; OF.renderString("string",["~","!","@","#","$","%","^","*","(",")","-","_","+","=","[","]","{","}","|",";",":","<",".",">","/","?"].choose);} ).add;
 
~numbers = OSCresponder.new(nil, "sched", { arg time, resp, msg, counter; counter = counter+1;OF.interactWithSound("numSoundSketches",counter);} ).add; 
~bigShoot = OSCresponder.new(nil, "/sched", { arg time, resp, msg;OF.effect("iperatou","shoot");} ).add; 
~cutMotion = OSCresponder.new(nil, "sched", { arg time, resp, msg; OF.image("stopMotion", Array.series(83, 0, 1).choose);} ).add; 

~iperatou.remove
~cutMotion.remove;
~type.remove;
~numbers.remove;
~bigShoot.remove;

OF.interactWithSound("numSoundSketches",1);
OF.renderString("string",["~","!","@","#","$","%","^","*","(",")","-","_","+","=","[","]","{","}","|",";",":","<",".",">","/","?"].choose);
OF.renderString("string",'rsldjfh')
OF.rgb("background", 0,0,0,2);

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
