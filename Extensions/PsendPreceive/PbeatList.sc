/* takes an array or a pattern, 
	and plays it sequentially once, inserting the following elements: 
	
	\start before the beginning of the pattern
	\end at the end of the pattern
	\beat n before each other event in the pattern. 

For use inside Psend



p = PbeatList(Pseq([1, 5, 7], 2)).asStream;

p.nextN(10);

b = PbeatList(Pseq([1, 5, 7], 2));

NotificationCenter.register(b, \end, 1, { "end now!!!!".postln });

Pbind(\degree, b, \dur, 1).play

Pbind(\degree, Pseq([1, Pwhite(-10, -3, 3), 7], 2)).play;



p = Psend(Pseq([\a, \b], 3)).play;


p = Psend(Pseq([\a, \b, Pn(\beat, 2)], 3)).play;
p = Psend(Pseq([\a, \b, Pwhite(1, 5, 2)], 3)).play;

p = Psend(Pseq([\a, \b, Pwhite(1, 3, 1), Pseq([\bli, \bla, \blo], 2)], 3)).play;

p = Psend(Pseq([\a, \b, Psend(Pseq([3], 1))], 3)).play;


p = Psend(Pseq([\a, 3, \b, 5], inf)).play;

p = Psend(Pseq([[\a], [\b]], inf)).play;
// // p = Psend(Pseq([[\a, Pwhite(0.1, 100.0, inf), 2, 3], [\b]], inf)).play;
p = Psend(Pseq([[\a, 1, 2, 3], \b, 5], inf)).play;
p = Psend(Pseq([[\a, 1, 2, 3], [\b], [['double', 1], ['chord']]], inf)).play;




p = Psend(Pseq([['alpha', 100], [['beta', 200], [\gamma, 300]]], inf)).play;
p = Psend(Pseq([['alpha', 100], [['beta', 200], [\gamma, 300]]], inf)).play;
p = Psend(Pseq([['alpha', 100], [['beta', 200], [\gamma, 300]]], inf)).play;


*/


PbeatList : Pattern {
	var <>pattern, <>name;
	*new { | pattern, name |
		^super.newCopyArgs(pattern, name.asSymbol);
	}
	embedInStream { arg inval;
		var stream, outval, beats = 0;
		stream = pattern.asStream;
		loop {
			outval = stream.next(inval);
			if (outval.isKindOf(Integer)) {
				// convert integers to series of beats
				outval do: {
					inval = [['beat', beats = beats + 1]].yield;
				};			
			}{
				if (outval.rank > 1) {
					// send chords
					inval.postln;
					inval = ([['beat', beats = beats + 1]] ++ outval).yield;
				}{
					// send single tags or tags with arguments
					outval.postln;
					inval = [['beat', beats = beats + 1], outval.asArray].yield;
				}
			}
		}
	}

/*
	embedInStreamOLD { arg inval;
		var stream, outval, beats = 0;
		stream = pattern.asStream;
		loop {
			outval = stream.next(inval);
			if (outval.isKindOf(Integer)) {
				// convert integers to series of beats
				outval do: {
					inval = [['beat', beats = beats + 1]].yield;
				};			
			}{
				if (outval.rank > 1) {
					// send chords
					inval = ([['beat', beats = beats + 1]] ++ outval).yield;
				}{
					// send single tags or tags with arguments
					outval.postln;
					inval = [['beat', beats = beats + 1], outval.asArray].yield;
				}
			}
		}
	}
*/
}