package com.github.attiand.jenkins.pipe.test.stubs

class Docker {
	def image(def name) {
		new Image()
	}
}

class Image {
	def inside(def body) {
		body.call()
	}
}
