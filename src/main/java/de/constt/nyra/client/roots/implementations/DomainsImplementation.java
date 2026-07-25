package de.constt.nyra.client.roots.implementations;

import java.net.IDN;
import java.util.*;

public class DomainsImplementation {
    private static final Set<String> DOMAINS = new HashSet<>(Set.of(
            "*.6b6t.org"
    ));

    public static void addDomain(String domain) {
        DOMAINS.add(domain);
    }

    public static void removeDomain(String domain) {
        DOMAINS.remove(domain);
    }

    public static void setDomains(Set<String> domains) {
        DOMAINS.clear();
        DOMAINS.addAll(domains);
    }

    public static Set<String> getDomains() {
        return DOMAINS;
    }

    public static boolean contains(String input) {
        String domain = normalizeHost(input);
        if (domain == null) {
            return false;
        }

        for (String entry : DOMAINS) {
            if (matchesNormalized(domain, entry)) {
                return true;
            }
        }

        return false;
    }

    public static boolean matches(String input, String pattern) {
        String domain = normalizeHost(input);
        String entry = normalizeEntry(pattern);
        return domain != null && entry != null && matchesNormalized(domain, entry);
    }

    private static String normalizeEntry(String input) {
        if (input == null) {
            return null;
        }

        String trimmed = input.trim();
        boolean wildcard = trimmed.startsWith("*.");
        String host = normalizeHost(wildcard ? trimmed.substring(2) : trimmed);
        return host == null ? null : (wildcard ? "*." : "") + host;
    }

    static String normalizeHost(String input) {
        if (input == null) {
            return null;
        }

        String host = input.trim();
        if (host.startsWith("*.")) {
            host = host.substring(2);
        }
        if (host.isEmpty()) {
            return null;
        }

        if (host.startsWith("[")) {
            int closingBracket = host.indexOf(']');
            if (closingBracket < 0 || !isValidPortSuffix(host.substring(closingBracket + 1))) {
                return null;
            }
            host = host.substring(1, closingBracket);
        } else {
            int firstColon = host.indexOf(':');
            int lastColon = host.lastIndexOf(':');
            if (firstColon >= 0 && firstColon == lastColon) {
                if (!isValidPortSuffix(host.substring(firstColon))) {
                    return null;
                }
                host = host.substring(0, firstColon);
            }
        }

        while (host.endsWith(".")) {
            host = host.substring(0, host.length() - 1);
        }
        if (host.isEmpty()) {
            return null;
        }

        try {
            // IPv6 literals contain colons and are not valid IDNs, but preserving them is safe.
            if (host.indexOf(':') >= 0) {
                return host.toLowerCase(Locale.ROOT);
            }
            String ascii = IDN.toASCII(host, IDN.USE_STD3_ASCII_RULES).toLowerCase(Locale.ROOT);
            return ascii.isEmpty() || ascii.length() > 253 ? null : ascii;
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    private static boolean isValidPortSuffix(String suffix) {
        if (suffix.isEmpty()) {
            return true;
        }
        if (suffix.charAt(0) != ':' || suffix.length() == 1) {
            return false;
        }

        try {
            int port = Integer.parseInt(suffix.substring(1));
            return port >= 1 && port <= 65_535;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    private static boolean matchesNormalized(String domain, String entry) {
        if (!entry.startsWith("*.")) {
            return domain.equals(entry);
        }

        String base = entry.substring(2);
        return domain.equals(base) || domain.endsWith("." + base);
    }
}
