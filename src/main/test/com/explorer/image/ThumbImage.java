package com.explorer.image;

import java.io.IOException;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ThumbImage {
	@Test
	public void genThumb(){
		try {
			
			Thumbnails.of("c:/01.jpg")
						.size(710,211)
						.sourceRegion(Positions.CENTER,711,211)//.size(710,211)
						.toFile("c:/01-1.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
