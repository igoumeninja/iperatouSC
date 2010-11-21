/* Example of a class 2

Igoumeninja 2010 11 17: 00:12 am


p = Posc(\msg, Pseq([['alpha', 100], ['beta', 200]], inf)).play;
p = Posc(\msg, Pseq([['alpha', 100], [['beta', 200], [\gamma, 300]]], inf)).play;

//Try to change NetAddr outside of the class 
Posc.address.ip = "127.0.0.1";
Posc.address.port = 57122;

*/

Posc : Pbind {
	classvar	<address;
	*init {
		Class.initClassTree(Event);
		Event.addEventType(\osc, {
			if (~msg.rank > 1) {
				~msg do: ~dest.sendBundle(~latency, _)
			}{
				~dest.sendBundle(~latency, ~msg);
			}
			});
	}

	*new { arg ... pairs; 	// add the type - osc pair, so we dont have to provide it explicitly
		//address = NetAddr("127.0.0.1", 57120);
		address = NetAddr("127.0.0.1", 12345);
		pairs = pairs addAll: [\type, \osc];
		if (pairs includes: \dest) {} { pairs = pairs addAll: [\dest, address] };
		^super.new(*pairs);
	}
}
