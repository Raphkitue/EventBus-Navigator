package fr.raphkitue.bussy.filter;

import com.intellij.usages.Usage;

/**
 * 过滤 Usages
 */
public interface Filter {
    boolean shouldShow(Usage usage);
}
