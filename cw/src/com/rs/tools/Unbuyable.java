package com.rs.tools;

import java.io.IOException;

import com.rs.cache.Cache;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.utils.Utils;

public class Unbuyable {
	public static void main(String[] args) throws IOException {
		Cache.init();
		for (int i = 0; i < Utils.getItemDefinitionsSize(); i++) {
			ItemDefinitions def = ItemDefinitions.getItemDefinitions(i);
				if(def.getName().contains("mask"))
				System.out.println(def.getLowerCaseName());
			}
		}
	}

