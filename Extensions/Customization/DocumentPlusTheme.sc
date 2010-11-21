
+ Document {
	*startup {
			var num, doc;
			allDocuments = [];
			num = this.numberOfOpen;
			num.do { | i | 
				doc = this.newFromIndex(i);
			};
			postColor = Color.black;
			themes = (
			
				oldDefault: (
					classColor: Color(0, 0, 0.75, 1),
					textColor: Color(0, 0, 0, 1),
					stringColor: Color(0.375, 0.375, 0.375, 1),
					commentColor: Color(0.75, 0, 0, 1),
					symbolColor: Color(0, 0.45, 0, 1),
					numberColor: Color(0, 0, 0, 1)
				),
				igoumeninjaTheme: 
				(
					classColor: Color.new255(175, 171, 255),
					textColor: Color.new255(255, 255, 255),
					stringColor: Color.new255(167,167,167),
					commentColor: Color.new255(225,170,79),
					symbolColor: Color.new255(160,170,45),
					numberColor: Color.new255(250,248,227)
				),
				default: 	(
					classColor: Color.new255(175, 171, 255),
					textColor: Color.new255(255, 255, 255),
					stringColor: Color.new255(167,167,167),
					commentColor: Color.new255(225,170,79),
					symbolColor: Color.new255(160,170,45),
					numberColor: Color.new255(250,248,227)
				)

			);
			theme = themes.default;
		}

	*new { | title="Untitled", string="", makeListener=false, envir | 
		var doc, env;
		env = currentEnvironment;
		if(this.current.notNil) { this.current.restoreCurrentEnvironment };		
		doc = Document.implementationClass.new(title, string, makeListener);
		Document.setTheme('igoumeninjaTheme');
		Document.theme;
		Document.current.background = Color(0, 0, 0, 0.97);

		if (doc.notNil) {
			doc.envir_(envir)
		} {
			currentEnvironment = env
		};
		
//		Document.current.background = Color(0, 0, 0, 0.97);
		^doc
	}
}