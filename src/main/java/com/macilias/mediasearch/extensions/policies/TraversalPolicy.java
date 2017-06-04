package com.macilias.mediasearch.extensions.policies;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/*
 * A custom FocusTraversalPolicy for MediaSearchGUI
 */
public class TraversalPolicy extends FocusTraversalPolicy {

	private static final Logger LOG = Logger.getLogger(TraversalPolicy.class);

	private final List<Component> components;
	private Component firstComponent;
	private Component lastComponent;

	// productivity options
	private List<String> excludedFromTraversalPolicyComponentNames;


	public TraversalPolicy(final List<Component> components) {
		this.components = components;
		firstComponent = components.get(1);
		lastComponent = components.get(components.size() - 1);
		excludedFromTraversalPolicyComponentNames = new LinkedList<String>();
	}


	public void excludeComponents(final List<String> componentNames) {
		excludedFromTraversalPolicyComponentNames.addAll(componentNames);
	}


	@Override
	public Component getComponentAfter(final Container con, final Component com) {
		if (com.equals(lastComponent) || com.equals(components.get(components.size() - 1))) {
			return firstComponent;
		}
		Component next = components.get((components.indexOf(com) + 1) % components.size());
		if (next.isEnabled() && !excludedFromTraversalPolicyComponentNames.contains(next.getName())) {
			next.requestFocus();
			return next;
		} else {
			return getComponentAfter(con, next);
		}
	}


	@Override
	public Component getComponentBefore(final Container con, final Component com) {
		Component prev = components.get((components.indexOf(com) + components.size() - 1) % components.size());
		if (prev.isEnabled() && !excludedFromTraversalPolicyComponentNames.contains(prev.getName())) {
			return prev;
		} else {
			return getComponentBefore(con, prev);
		}
	}


	@Override
	public Component getFirstComponent(final Container con) {
		return firstComponent;
	}


	public void setFirstComponent(final Component com) {
		firstComponent = com;
	}


	public void setFirstComponent(final String comName) {
		for (Component component : components) {
			if (component.getName().equals(comName)) {
				firstComponent = component;
				component.requestFocusInWindow();
				break;
			}
		}
	}


	@Override
	public Component getLastComponent(final Container con) {
		return lastComponent;
	}


	public void setLastComponent(final Container con) {
		lastComponent = con;
	}


	public void setLastComponent(final String comName) {
		for (Component component : components) {
			if (component.getName().equals(comName)) {
				lastComponent = component;
				break;
			}
		}
	}


	@Override
	public Component getDefaultComponent(final Container con) {
		return getFirstComponent(con);
	}


	public List<Component> getComponents() {
		return components;
	}


	public List<String> getExcludedFromTraversalPolicyComponentNames() {
		return excludedFromTraversalPolicyComponentNames;
	}


	public void setExcludedFromTraversalPolicyComponentNames(final List<String> excludedFromTraversalPolicyComponentNames) {
		this.excludedFromTraversalPolicyComponentNames = filterNotCorrectNames(excludedFromTraversalPolicyComponentNames);
	}


	private List<String> filterNotCorrectNames(final List<String> excludedFromTraversalPolicyComponentNames) {
		List<String> correctNames = new ArrayList<String>(excludedFromTraversalPolicyComponentNames.size());
		for (String excludedFromTraversalPolicyComponentName : excludedFromTraversalPolicyComponentNames) {
			if (knownComponentName(excludedFromTraversalPolicyComponentName)) {
				correctNames.add(excludedFromTraversalPolicyComponentName);
			}
		}
		return correctNames;
	}


	private boolean knownComponentName(String excludedFromTraversalPolicyComponentName) {
		for (Component component : components) {
			if (component.getName().equals(excludedFromTraversalPolicyComponentName)) {
				return true;
			}
		}
		LOG.warn(String.format("There is no component with the name: %s", excludedFromTraversalPolicyComponentName));
		return false;
	}


	public void removeExcludedComponentName(final String selectedValue) {
		if (excludedFromTraversalPolicyComponentNames != null) {
			excludedFromTraversalPolicyComponentNames.remove(selectedValue);
		}
	}
}
