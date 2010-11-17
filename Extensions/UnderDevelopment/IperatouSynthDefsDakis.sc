/* Draft of simple class for synthdefs for a project 


Iperatou1DakisSynthDefs.load;

*/

Iperatou1DakisSynthDefs {
	*load {
		var s;
		var winenv, z;
		var b, c, d, g;
		s = Server.default;
		winenv = Env([0, 1, 0], [0.5, 0.5], [8, -8]);
		z = Buffer.sendCollection(s, winenv.discretize, 1);
		
		SynthDef(\in_grain, {arg gate = 1, amp = 1, envbuf, newtrig = 0.5, durtrig = 0.25;
			var pan, env;Œ
			pan = 0;
			env = EnvGen.kr(
				Env([0, 1, 0], [1, 1], \sin, 1),
				gate,
				levelScale: amp,
				doneAction: 2);
			Out.ar(0,
				GrainIn.ar(2, Impulse.ar(newtrig), durtrig, In.ar(22, 2) * 1, pan, envbuf) * env)
			}).load(s);
			
		SynthDef("moogbot2", { arg out = 0, vol = 0.1, freq1 = 3, freq2 = 5, freq = 47, gate = 1, rls = 0.04, amp = 0.4, rqq = 0.6, att = 0.002;
			var input, filter;
			input = Resonz.ar( 
				WhiteNoise.ar(EnvGen.kr((Env.perc(att, rls)), gate, doneAction: 2) * amp), freq.midicps, rqq, 4); 
			filter =  MoogFF.ar(input, SinOsc.kr(freq1, pi, 40, 20000), SinOsc.kr(freq2, 0, 4, 0.5).abs);
			Out.ar(out, filter ! 2 * vol);
		 }).add;
		
		SynthDef(\reverb1, { | out, roomsize = 16, revtime = 1.24, damping = 0.1, inputbw = 0.19, spread = 15,
				drylevel = -3, earlylevel = -9, taillevel = -11 |
			var input;
			input = In.ar(20, 2);
			Out.ar(out, GVerb.ar(
				input,
				roomsize,
				revtime,
				damping,
				inputbw,
				spread,
				drylevel.dbamp,
				earlylevel.dbamp,
				taillevel.dbamp,
				roomsize, 0.3) + input
					)
			}).load(s);
		
		/*
		~sin1 = SynthDef("episaw", { | out = 0, xFade = 1 |
			var input;
			input =In.ar([0, 1], 2) * VarSaw.kr(LFNoise1.kr(8, 780, 820), 0, MouseY.kr(0.002, 50), 0.02, 0);
			Out.ar(out, xFade, Balance2.ar(input, input, LFNoise0.kr(8, 1, 0, 0.3))) * 1;
		}).send(s);
		*/
		
		SynthDef("task1", { |freq, amp = 1, sustain = 1.1, pan = 0, brown = 0.1, saw = 0, sin1 = 0, sin2 = 0.1, attime = 0.003,
			 rlstime = 0.1, out = 0 |
			var in, osc, env, ses;
			env =  EnvGen.ar(Env.perc(attime, rlstime), doneAction: 2, levelScale: 0.8, timeScale: sustain);
			in = SinOsc.ar(FSinOsc.ar(200, 0, brown*2)/8, 1.4);
			ses = SinOsc.ar(0, in, 0.01) ;
			ses = RLPF.ar(ses, freq, 1.4, 1.6, 0.4 );
			ses =  ses.sin/4 + SinOsc.ar(freq, Decay.ar(SinOsc.ar(sin1, sin2), 4.2.round, Saw.ar(saw)));
			ses = Limiter.ar(ses, 0.6, 0.01);
			ses = DelayN.ar( ses, 2.8, 0.01, 1.5, 0.0, 1.1, ses);
			Out.ar(out, ses *amp *env ! 2);
		}).add; 
		
		SynthDef( \tascale2, { |freq = 55, amp = 1, attime = 0.01, rls = 0.09, pan = 0, brown = 0.1, widthdur = 0.5  |
			var in, osc, env, ses;
			env =  EnvGen.ar(Env.perc(attime, rls, 0.2, 2), doneAction: 2);
			in = VarSaw.ar(freq, 0, SinOsc.kr(widthdur, 0, 0.9, 0).abs, brown);
			osc = SinOsc.ar(0, in, 0.2) * env;
			ses = Pan2.ar(osc, pan, FSinOsc.kr(1.5));
			Out.ar(0, ses *4 *amp);
		}).add;
		
		SynthDef("oou", { arg out = 22, amp = 0.003, fr1 = 82.41, fr2 = 130.8, fr3 = 196, fr4 = 738, rg1 = 0.01,
										rg2 = 0.01, rg3 = 0.01, rg4 = 0.01, ftoner = 1, rtimer = 1, dstr = 2;
			var freqs, ringtimes, input;
			freqs = [fr1, fr2, fr3, fr4] * ftoner;
			ringtimes = [rg1, rg2, rg3, rg4] * rtimer;
			input = DynKlank.ar(`[freqs, nil, ringtimes], PinkNoise.ar(amp)).distort * dstr;
			Out.ar(out, input ! 2);
		}).load(s);
		//newtrig 0.5 - 20
		//durtrig 0.25 - 0.05
		
		g = SynthDef("grr", { arg out=0, bufnumA=0, bufnumB=1, soundBufnum=2, speed = 1.5, freq = 55, randwipe = 0.85, iph = 0, zer = 0.0001, amp = 0.001, rate = 1;
			var inA, chainA, inB, chainB, chain;
			//inA = Impulse.ar([freq, freq + 1] iph, amp);
			inA = LFSaw.ar(freq, iph, amp);
			inB = PlayBuf.ar(1, soundBufnum, rate, BufRateScale.kr(soundBufnum), loop: 1);
			chainA = FFT(bufnumA, inA);
			chainA = PV_RandComb(chainA, randwipe, Impulse.kr(speed)); 
			chainB = FFT(bufnumB, inB);
			chain = PV_MagDiv(chainA, chainB, zer); 
			Out.ar(out,  0.5 * IFFT(chain) ! 2);
		}).load(s);
		
		
		SynthDef( \tascale2, { |freq = 55, amp = 1, attime = 0.01, rls = 0.09, pan = 0, brown = 0.1, widthdur = 0.5  |
			var in, osc, env, ses;
			env =  EnvGen.ar(Env.perc(attime, rls, 0.2, 2), doneAction: 2);
			in = VarSaw.ar(freq, 0, SinOsc.kr(widthdur, 0, 0.9, 0).abs, brown);
			osc = SinOsc.ar(0, in, 0.2) * env;
			ses = Pan2.ar(osc, pan, FSinOsc.kr(1.5));
			Out.ar(0, ses *4 *amp);
		}).add;
		
		
		SynthDef(\buf, { | out=0, bufnum = 0, rate = 1, startPos = 0, amp = 1.0, sustain = 1, pan = 0, loop = 1|
			var audio;
			rate = rate * BufRateScale.kr(bufnum);
			startPos = startPos * BufFrames.kr(bufnum);
			
			audio = BufRd.ar(1, bufnum, Phasor.ar(0, rate, startPos, BufFrames.ir(bufnum)), 1, 2);
			audio = EnvGen.ar(Env.sine, 1, timeScale: sustain, doneAction: 2) * audio;
			audio = Pan2.ar(audio, pan, amp*4);
			OffsetOut.ar(out, audio);
		}).add;
		
		SynthDef(\kick1, { |preamp = 1, amp = 1, freq1 = 400, freq2 = 66|
			var	freq = EnvGen.kr(Env([freq1, freq2], [0.08], -3)),
				sig = SinOsc.ar(freq, 0.5pi, preamp).distort * amp
					* EnvGen.kr(Env([0, 1, 0.8, 0], [0.01, 0.1, 0.2]), doneAction: 2);
			Out.ar(0, sig ! 2);
		}).add;
		
		
		SynthDef(\play1, {| out = 0, bufnum, amp = 1|
			Out.ar(out, 
				PlayBuf.ar(2, bufnum, BufRateScale.kr(bufnum), loop: 1.0) * amp
			)
		}).send(s);
		
		SynthDef(\play2, {| out = 0, bufnum, amp = 1|
			Out.ar(out, 
				PlayBuf.ar(2, bufnum, BufRateScale.kr(bufnum), loop: 1.0)  * amp
			)
		}).send(s);
		
		SynthDef(\play3, {| out = 0, bufnum, amp = 1|
			Out.ar(out, 
				PlayBuf.ar(2, bufnum, BufRateScale.kr(bufnum), loop: 1.0)  * amp
			)
		}).send(s);
		
		SynthDef(\play4, {| out = 0, bufnum, amp = 1 |
			Out.ar(out, 
				PlayBuf.ar(2, bufnum, BufRateScale.kr(bufnum), loop: 1.0)  * amp
			)
		}).send(s);
	}
}