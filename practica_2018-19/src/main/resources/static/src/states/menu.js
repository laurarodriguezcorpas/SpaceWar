Spacewar.menuState = function(game) {
	var textoNombreUsuario;
}

Spacewar.menuState.prototype = {

	init : function() {
		if (game.global.DEBUG_MODE) {
			console.log("[DEBUG JS] Entering **MENU** state");
		}
	},

	preload : function() {
		// In case JOIN message from server failed, we force it
		var background = game.load.image('background','assets/images/menuWallpaper.jpg');
		var logo = game.load.image('logoSpaceWars','assets/images/logoSpaceWars.png');
		game.load.image('buttonStart','assets/images/buttonStart.png');
		
		if (typeof game.global.myPlayer.id == 'undefined') {
			if (game.global.DEBUG_MODE) {
				console.log("[DEBUG JS] Forcing joining server...");
			}
			let message = {
				event : 'JOIN'
			}
			game.global.socket.send(JSON.stringify(message))

		}
		game.add.plugin(PhaserInput.Plugin);
	},
	
	create : function() {
		background = game.add.tileSprite(0, 0, 1200, 800, 'background');
		logoSpaceWars = game.add.tileSprite(130, 60, 1097, 107, 'logoSpaceWars');
		logoSpaceWars.scale.setTo(0.7, 0.7);
		var buttonStart = game.add.button(game.world.centerX - 75, 400, 'buttonStart', matchmaking, this, 2, 1, 0);
		buttonStart.scale.setTo(0.5, 0.5);
		
		textoNombreUsuario  = game.add.inputField(game.world.centerX-110, 250, {
		    font: '18px Arial',
		    fill: '#212121',
		    fontWeight: 'bold',
		    width: 200,
		    padding: 8,
		    borderWidth: 1,
		    borderColor: '#000',
		    borderRadius: 6,
		    placeHolder: 'Introduce tu nombre: ',
		});
		
		textoNombreUsuario.startFocus();
		if (game.global.DEBUG_MODE) 
			console.log("[DEBUG JS] Recoger aquí el nombre")
		textoNombreUsuario.endFocus();
		
		function matchmaking () {
			game.state.start('gameModeState')
		}
		
		PhaserInput.onKeyboardClose.addOnce(function() {
		    this.resizeBackgroundImage();
		});
		
	},


	update : function() {
		textoNombreUsuario.update();
		/*
		if (typeof game.global.myPlayer.id !== 'undefined') {
			game.state.start('lobbyState')
		}	*/
	}
}