
/* Draft of simple class for buffers for a project 


Iperatou1DakisBuffers.load;

Iperatou1DakisBuffers.at(\buffer1);
Iperatou1DakisBuffers.at(\buffer1, \buffer2);


*/

Iperatou1DakisBuffers {
	classvar <buffers;
	*load {
		var s;
		var b, c, d, g;

		buffers = IdentityDictionary.new;

		s = Server.default;

		buffers[\b] = Buffer.alloc(s,2048,1);
		buffers[\c] = Buffer.alloc(s,2048,1);
		buffers[\d]  = Buffer.read(s,"sounds/kick1.wav");
		buffers[\buffer1] = Buffer.read(s, "sounds/me_piano1.aiff");
		buffers[\buffer2] = Buffer.read(s, "sounds/me_piano2.aiff");
		buffers[\buffer3] = Buffer.read(s, "sounds/me_piano3.aiff");
		buffers[\buffer4] = Buffer.read(s, "sounds/me_piano4.aiff");

	}
	
	*at { | name |
		
		^buffers.at(name.asSymbol);
	}
	
	*atN { | ... names |
		^names collect: { | name | buffers.at(name.asSymbol); };
	}
	
	
}