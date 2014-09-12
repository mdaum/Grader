package token.command;

import token.WordToken;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "rotateRightArm" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String", "Word" })
@EditablePropertyNames({ "String" })
public class RotateRightArmCommandToken extends WordToken implements
		IRotateRightArmCommandToken {

	public RotateRightArmCommandToken(String s) {
		super(s);
	}

}
