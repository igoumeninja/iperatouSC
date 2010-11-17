

IperatouDakisSetup {
	
	classvar <olokliro;
	
	*load {
		Iperatou1DakisSynthDefs.load;
		Iperatou1DakisBuffers.load;	
	}
	
	*makeOlokliro {
		olokliro = IperatouDakisOlokliro.make;	
	}
	
	*start {
		
	}
	
	*stop {
		
	}
	
}