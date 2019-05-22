Spacewar.menuState = function(game) {

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
		game.load.image('buttonStart','assets/images/buttonStart.png');
		
		if (typeof game.global.myPlayer.id == 'undefined') {
			if (game.global.DEBUG_MODE) {
				console.log("[DEBUG] Forcing joining server...");
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
		var buttonClassic = game.add.button(game.world.centerX - 85, 400, 'buttonStart', unirseARoom, this, 2, 1, 0);
		buttonClassic.scale.setTo(0.5, 0.5);
		
		var name = game.add.inputField(10, 90, {
		    font: '18px Arial',
		    fill: '#212121',
		    fontWeight: 'bold',
		    width: 150,
		    padding: 8,
		    borderWidth: 1,
		    borderColor: '#000',
		    borderRadius: 6,
		    placeHolder: 'NOMBRA A TU TROPitA: ',
		});
		
		let msg = new Object()
		msg.event = 'NAME'

		msg.playerName = {
			n: name
		}
		

		function unirseARoom () {
			
			if (game.global.DEBUG_MODE) {
				console.log("[DEBUG JS] Sending NAME message to server")
			}
			game.global.socket.send(JSON.stringify(msg))
			
			game.state.start('roomState')
		}
	},


	update : function() {
		/*
		if (typeof game.global.myPlayer.id !== 'undefined') {
			game.state.start('lobbyState')
		}	*/
	}
}