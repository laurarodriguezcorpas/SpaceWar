Spacewar.menuState = function(game) {

}

Spacewar.menuState.prototype = {

	init : function() {
		if (game.global.DEBUG_MODE) {
			console.log("[DEBUG] Entering **MENU** state");
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
	},
	
	create : function() {
		background = game.add.tileSprite(0, 0, 1200, 800, 'background');
		var buttonClassic = game.add.button(game.world.centerX - 85, 400, 'buttonStart', unirseARoom, this, 2, 1, 0);
		buttonClassic.scale.setTo(0.5, 0.5);
		
		
		function unirseARoom () {
			game.state.start('gameState')
		}
	},


	update : function() {
		/*
		if (typeof game.global.myPlayer.id !== 'undefined') {
			game.state.start('lobbyState')
		}	*/
	}
}