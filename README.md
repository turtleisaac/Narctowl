# NARCtowl

Narc Unpacker and Packer for Nintendo DS Pokémon Games

Thanks/ Credits to:
 * PlatinumMaster for writing the python NARCTool version I used as a reference
 * Vendor, FrankieD, and Zekromaegis for updating and releasing new versions of PlatinumMaster's NARCTool
 * FrankieD for assisting me as we figured out how to read a narc and correctly extract other files from it

Usage is as follows: java -jar NARCtowl.jar <arguments> 
 * Unpacking: java -jar NARCtowl.jar unpack <file name (include .narc)> 
 * Packing: java -jar NARCtowl.jar pack <name of directory in extracted folder> <name of file to create (do not include .narc)> 
  * Note: Narc must be in the same folder as NARCtowl.jar 
  * Note: Directory to construct narc from must be in "extracted" directory
