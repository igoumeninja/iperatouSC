/*
Meros B

~photo slide show
*/

OSCresponder.new(nil, "sched", { arg time, resp, msg; OF.image("stopMotion", Array.series(83, 0, 1).choose);} ).add; 
(
~beatPhraseMerosB = [
	SyncAction(\beats, { | beat |
		beat.postln;
		switch (beat,
			1, {
				OF.rgb("background", 0,0,0,20);
			},

			480, { 
			}
		);	
		if (beat > 0 and: { beat < 600;} and: { beat % 1 == 0;}) { OF.image("stopMotion", Array.series(83, 0, 1).choose); };		
	})
];
)
~partB = SyncSender(Pbind.new);
~partB.start;
~partB.stop;
~beatPhraseMerosB do: _.deactivate;
~beatPhraseMerosB do: _.activate;