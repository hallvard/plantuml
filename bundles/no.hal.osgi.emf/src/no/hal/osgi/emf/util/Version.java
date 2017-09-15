package no.hal.osgi.emf.util;

public class Version implements Comparable<Version> {
	
	public final int major, minor, micro;
	public final String qualifier;
	
	public Version(int major, int minor, int micro, String qualifier) {
		this.major = major;
		this.minor = minor;
		this.micro = micro;
		this.qualifier = qualifier;
	}

	@Override
	public String toString() {
		String result = Integer.toString(major);
		if (minor >= 0) {
			result += "." + minor;
			if (micro >= 0) {
				result += "." + micro;
				if (qualifier != null) {
					result += "." + qualifier;
				}
			}
		}
		return result;
	}
	
	public static Version valueOf(String s) {
		String[] segments = s.split("\\.");
		if (segments.length > 4) {
			throw createIllegalSyntaxException(s);
		}
		try {
			int major = Integer.valueOf(segments[0]);
			int minor = (segments.length > 1 ? Integer.valueOf(segments[1]) : -1);
			int micro = (segments.length > 2 ? Integer.valueOf(segments[2]) : -1);
			String qualifier = (segments.length > 3 ? segments[3] : null);
			return new Version(major, minor, micro, qualifier);
		} catch (NumberFormatException e) {
			throw createIllegalSyntaxException(s);
		}
	}

	private static RuntimeException createIllegalSyntaxException(String s) {
		return new IllegalArgumentException("Illegal version: " + s + ". A version has the following format: major.minor.micro.qualifier, where major, minor and micro are integers");
	}

	@Override
	public int compareTo(Version other) {
		int diff = major - other.major;
		if (diff == 0) {
			diff = minor - other.minor;			
			if (diff == 0) {
				diff = micro - other.micro;			
			}
		}
		return diff;
	}
}
