package no.hal.osgi.emf.util;

public class VersionRange {
	
	public final Version minimum, maximum;
	public final boolean minimumExclusive, maximumExclusive;

	public VersionRange(Version minimum, Version maximum, boolean minimumExclusive, boolean maximumExclusive) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.minimumExclusive = minimumExclusive;
		this.maximumExclusive = maximumExclusive;
	}

	@Override
	public String toString() {
		if (minimum != null) {
			if (maximum != null && minimum.compareTo(maximum) == 0) {
				return minimum.toString();
			}
			return (minimumExclusive ? "(" : "[") + minimum + (maximum != null ? "," + maximum : "") + (maximumExclusive ? ")" : "]"); 
		}
		return null;
	}
	
	public static VersionRange valueOf(String s) {
		Version minimum = null, maximum = null;
		boolean minimumExclusive = false, maximumExclusive = false;
		int pos1 = 0;
		char firstChar = s.charAt(pos1);
		if (Character.isDigit(firstChar)) {
		} else if (firstChar == '[') {
			minimumExclusive = true;
			pos1++;
		} else if (firstChar == '(') {
			minimumExclusive = false;
			pos1++;
		} else {
			throw createIllegalSyntaxException();
		}
		int pos2 = pos1;
		char nextChar = '\0';
		while (pos2 < s.length()) {
			nextChar = s.charAt(pos2);
			if (! (Character.isDigit(nextChar) || nextChar == '.')) {
				break;
			}
			pos2++;
		}
		minimum = Version.valueOf(s.substring(pos1, pos2));
		pos1 = pos2;
		if (pos1 < s.length()) {
			firstChar = s.charAt(pos1);
			if (firstChar == ',') {
				pos1++;
				pos2 = pos1;
				while (pos2 < s.length()) {
					nextChar = s.charAt(pos2);
					if (! Character.isDigit(nextChar)) {
						break;
					}
					pos2++;
				}
				maximum = Version.valueOf(s.substring(pos1, pos2));
				pos1 = pos2;
			} else if (firstChar == ']') {
				maximumExclusive = true;
				pos1++;
			} else if (firstChar == ')') {
				maximumExclusive = false;
				pos1++;
			} else {
				throw createIllegalSyntaxException();
			}
			if (pos1 < s.length()) {
				throw createIllegalSyntaxException();
			}
		}
		return new VersionRange(minimum, maximum, minimumExclusive, maximumExclusive);
	}

	private static RuntimeException createIllegalSyntaxException() {
		return new IllegalArgumentException("A version has the following format: major.minor.micro.qualifier, where major, minor and micro har integers");
	}

	public boolean contains(Version version) {
		return true;
	}
}
