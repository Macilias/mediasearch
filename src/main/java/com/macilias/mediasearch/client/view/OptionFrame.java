package com.macilias.mediasearch.client.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.macilias.mediasearch.client.model.enumerations.Environment;
import com.macilias.mediasearch.client.model.enumerations.TargetSystem;
import com.macilias.mediasearch.client.model.facebook.FBConnection;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

//import de.espirit.common.LOG;
import com.macilias.mediasearch.client.MediaSearchClient;
import com.macilias.mediasearch.client.controller.UserSettingsController;
import com.macilias.mediasearch.client.model.Connection;
import com.macilias.mediasearch.client.model.ExcludedComponentListModel;
import com.macilias.mediasearch.client.model.FavouriteSearchesListModel;
import com.macilias.mediasearch.client.model.MediaSearch;
import com.macilias.mediasearch.client.model.enumerations.MediaSearchIcon;
import com.macilias.mediasearch.client.model.enumerations.PerformanceLevel;
import com.macilias.mediasearch.client.model.enumerations.Resolution;
import com.macilias.mediasearch.client.model.enumerations.UserData;
//import de.guj.ocm.mediasearch.client.model.firstspirit.FSConnection;
import com.macilias.mediasearch.client.model.interfaces.MethodCaller;
import com.macilias.mediasearch.client.model.interfaces.SearchCaller;
import com.macilias.mediasearch.client.model.interfaces.SearchManager;
import com.macilias.mediasearch.extensions.layout.CenteredFrame;
import com.macilias.mediasearch.extensions.policies.TraversalPolicy;

public class OptionFrame implements MethodCaller {

	private static final Logger LOG = Logger.getLogger(OptionFrame.class);
	
	private JPanel optionPanel;
	private JPanel centerPanel;
	private JTabbedPane optionTabbedPane;
	private JPanel performanceTab;
	private JLabel maxMemoryLabel;
	private JTextField maxMemoryTextField;
	private JLabel maxMemoryLabel2;
	private JPanel memoryPane;
	private JLabel maxPictureLabel;
	private JComboBox maxPictureComboBox;
	private JPanel scalarPane;
	private JLabel methodLabel;
	private JComboBox methodComboBox;
	private JLabel modeLabel;
	private JComboBox modeComboBox;
	private JList availableComponentsList;
	private JScrollPane tabbingListScrollPanel1;
	private JPanel productivityTyp;
	private JList excludedComponentsList;
	private JScrollPane tabbingListScrollPanel2;
	private JPanel controllPane;
	private JButton saveButton;
	private JPanel connectionTab;
	private JLabel serverLabel;
	private JLabel projectLabel;
	private JLabel userLabel;
	private JTextField userTextField;
	private JLabel passwordLabel;
	private JLabel portLabel;
	private JTextField portTextField;
	private JPasswordField passwordField;
	private JButton connectionButton;
	private JTextField serverTextField;
	private JTextField projectTextField;
	private JComboBox predefinedConnectionsComboBox;
	private JLabel predefinedConnectionsLabel;
	private JComboBox predefinedPerformancesComboBox;
	private JPanel favouriteSearchesTab;
	private JScrollPane favouriteSearchesListScrollPane1;
	private JScrollPane favouriteSearchesListScrollPane2;
	private JList availableFavouriteSearchesList;
	private JList visibleFavouriteSearchesList;
	private JPanel favouriteControllPane;
	private JButton favouriteAddButton;
	private JButton favouriteRemoveButton;
	private JPanel tabbingControllPanel;
	private JButton tabbingAddButton;
	private JButton tabbingRemoveButton;
	private JPanel favouriteControllPane2;
	private JButton favouriteSaveButton;
	private JPanel tabbingControllPanel2;
	private JButton tabbingSaveButton;
	private JPanel productivityPanel;
	private JLabel firstComponentLabel;
	private JComboBox firstComponentComboBox;
	private JLabel lastComponentLabel;
	private JComboBox lastComponentComboBox;
	private JLabel activeSearchLabel;
	private JCheckBox activeSearchCheckBox;
	private JLabel targetSystemLabel;
	private JComboBox targetSystemComboBox;
	private JLabel autoSaveLabel;
	private JCheckBox autoSaveCheckBox;
	private JLabel inactiveSearchLabel;
	private JCheckBox inactiveSearchCheckBox;
	private JCheckBox windowPositionCheckBox;
	private JLabel windowPositionLabel;
	private JCheckBox galleryPositionCheckBox;
	private JLabel galleryPositionLabel;
	private CenteredFrame frame;

	private final TraversalPolicy focusTraversalPolicy;
	private Scalr.Method scalingMethod;
	private Scalr.Mode scalingMode;
	private Resolution maxPictureResolution;
	private int maxMemoryInMB;
	private final SearchCaller caller;
	private final SearchManager manager;
	private Connection connection;


	public OptionFrame(final TraversalPolicy focusTraversalPolicy, final Resolution maxPictureResolution, final Scalr.Method scalingMethod, final Scalr.Mode scalingMode) {
		this.focusTraversalPolicy = focusTraversalPolicy;
		this.scalingMethod = scalingMethod;
		this.scalingMode = scalingMode;
		this.maxPictureResolution = maxPictureResolution;
		caller = MediaSearchGUI.getInstance();
		manager = MediaSearchGUI.getInstance();
		maxMemoryInMB = manager.getMaximalSpaceInMegaByte();
		connection = MediaSearchClient.getInstance().getClientConnection();
		$$$setupUI$$$();
		if (connection.isStandAlone()) {
			optionTabbedPane.add("Verbindungen", connectionTab);
			connectionTab.setEnabled(true);
		} else {
			optionTabbedPane.remove(connectionTab);
			connectionTab.setEnabled(false);
		}
		fillLabels();
		fillComboBoxes();
		fillLists();
		fillCheckBoxes();
		manageActionListeners();
		manageKeyListeners();
	}


	private void manageKeyListeners() {
		saveButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					saveButton.doClick();
					saveButton.requestFocusInWindow();
				}
			}
		});
		connectionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				reconnect();
			}
		});
		connectionButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					connectionButton.doClick();
					connectionButton.requestFocusInWindow();
				}
			}
		});
		visibleFavouriteSearchesList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
					removeFromVisibleSearches();
				}
			}
		});
		availableFavouriteSearchesList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
					removeFromAvailableSearches();
				}
			}
		});
	}


	private void manageActionListeners() {
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				activateAndSavePerformanceOptions();
			}
		});
		favouriteSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				activateAndSaveFavouriteOptions();
			}
		});
		tabbingSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				activateAndSaveProductivityOptions();
			}
		});
		predefinedConnectionsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				setConnection(((Environment) predefinedConnectionsComboBox.getSelectedItem()).getConnection());
			}
		});
		predefinedPerformancesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				setUIAccordingToPerformanceLevel((PerformanceLevel) predefinedPerformancesComboBox.getSelectedItem());
			}
		});
		favouriteAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				moveFromAvailableToVisible();
			}
		});
		favouriteRemoveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				moveFromVisibleToAvailable();
			}
		});
		tabbingAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				moveComponentFromAvailableToExcluded();
			}
		});
		tabbingRemoveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent actionEvent) {
				removeComponentFromExcluded();
			}
		});
		activeSearchCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (activeSearchCheckBox.isSelected()) {
					inactiveSearchCheckBox.setSelected(false);
				}
			}
		});
		inactiveSearchCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (inactiveSearchCheckBox.isSelected()) {
					activeSearchCheckBox.setSelected(false);
				}
			}
		});
	}


	private void removeFromAvailableSearches() {
		List<MediaSearch> availableFavouriteSearches = manager.getAvailableFavouriteSearches();
		for (Object selectedValue : availableFavouriteSearchesList.getSelectedValues()) {
			MediaSearch mediaSearch = (MediaSearch) selectedValue;
			availableFavouriteSearches.remove(mediaSearch);
		}
		manager.setAvailableFavouriteSearches(availableFavouriteSearches);
		fillAvailableSearches();
	}


	private void removeFromVisibleSearches() {
		List<MediaSearch> visibleFavouriteSearches = manager.getVisibleFavouriteSearches();
		for (Object selectedValue : visibleFavouriteSearchesList.getSelectedValues()) {
			MediaSearch mediaSearch = (MediaSearch) selectedValue;
			visibleFavouriteSearches.remove(mediaSearch);
		}
		manager.setVisibleFavouriteSearches(visibleFavouriteSearches);
		fillVisibleSearches();
	}


	private void removeComponentFromExcluded() {
		for (Object selectedValue : excludedComponentsList.getSelectedValues()) {
			focusTraversalPolicy.removeExcludedComponentName((String) selectedValue);
		}
		fillFocusPolicy();
		fillExcludedFocusPolicy();
	}


	private void moveComponentFromAvailableToExcluded() {
		ExcludedComponentListModel listModel = (ExcludedComponentListModel) excludedComponentsList.getModel();
		List<String> toRemove = new LinkedList<String>();
		for (Object selectedValue : availableComponentsList.getSelectedValues()) {
			listModel.addElement(selectedValue);
			toRemove.add((String) selectedValue);
		}
		focusTraversalPolicy.excludeComponents(toRemove);
		fillFocusPolicy();
	}


	private void moveFromVisibleToAvailable() {
		List<MediaSearch> availableFavouriteSearches = manager.getAvailableFavouriteSearches();
		List<MediaSearch> visibleFavouriteSearches = manager.getVisibleFavouriteSearches();
		for (Object selectedValue : visibleFavouriteSearchesList.getSelectedValues()) {
			MediaSearch mediaSearch = (MediaSearch) selectedValue;
			availableFavouriteSearches.add(mediaSearch);
			visibleFavouriteSearches.remove(mediaSearch);
		}
		manager.setAvailableFavouriteSearches(availableFavouriteSearches);
		manager.setVisibleFavouriteSearches(visibleFavouriteSearches);
		fillAvailableSearches();
		fillVisibleSearches();
	}


	private void moveFromAvailableToVisible() {
		List<MediaSearch> availableFavouriteSearches = manager.getAvailableFavouriteSearches();
		List<MediaSearch> visibleFavouriteSearches = manager.getVisibleFavouriteSearches();
		for (Object selectedValue : availableFavouriteSearchesList.getSelectedValues()) {
			MediaSearch mediaSearch = (MediaSearch) selectedValue;
			if (visibleFavouriteSearches.size() <= 5) {
				visibleFavouriteSearches.add(mediaSearch);
				availableFavouriteSearches.remove(mediaSearch);
			} else {
				JOptionPane.showMessageDialog(optionPanel, "Es können nur 6 Suchen gleichzeitig angezeigt werden, \nbitte verschieben Sie erst eine Suche aus den Ausgewählten in die vorhanden um Platz zu schaffen.", "Hinweis", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(MediaSearchIcon.BOOKMARK_ICON.getImageData()));
				break;
			}
		}
		manager.setAvailableFavouriteSearches(availableFavouriteSearches);
		manager.setVisibleFavouriteSearches(visibleFavouriteSearches);
		fillAvailableSearches();
		fillVisibleSearches();
	}


	private void setUIAccordingToPerformanceLevel(final PerformanceLevel performanceLevel) {
		maxMemoryTextField.setText(String.valueOf(performanceLevel.getMemory()));
		maxPictureComboBox.setSelectedItem(performanceLevel.getResolution());
		methodComboBox.setSelectedItem(performanceLevel.getScalingMethod());
		modeComboBox.setSelectedItem(performanceLevel.getScalingMode());
	}


	private void setConnection(final Connection connection) {
		this.connection = connection;
		serverTextField.setText(connection.getServerName());
		portTextField.setText(String.valueOf(connection.getPort()));
		userTextField.setText(connection.getUser());
		passwordField.setText(connection.getPassword());
		if (connection.getTargetSystem() == TargetSystem.FB) {
			LOG.warn("not implemented yet");
		}
	}


	private void reconnect() {
		if (isReconnectPossible()) {
			String server = serverTextField.getText();
			int port = getPort();
			String user = userTextField.getText();
			String pw = String.valueOf(passwordField.getPassword());
			TargetSystem targetSystem = (TargetSystem) targetSystemComboBox.getSelectedItem();
			manager.lockConnection();
			Connection newConnection = null;
			switch (targetSystem) {
				default:
				case FB:
					newConnection = new FBConnection(targetSystem, server, user, pw, port, true);
			}
			manager.useConnection(newConnection);
		}
	}


	private boolean isReconnectPossible() {
		boolean reconnectPossible;
		reconnectPossible = handlePossiblyRunningQuery();
		if (reconnectPossible) {
			reconnectPossible = handlePossiblyRunningFetching();
		}
		return reconnectPossible;
	}


	private boolean handlePossiblyRunningQuery() {
		if (caller.isQueryInProgress()) {
			JOptionPane.showMessageDialog(optionPanel, "Bevor die Verbindung geändert werden kann, muss erst die aktuell laufende Suche abgebrochen oder beendet werden.", MediaSearchGUI.DIALOG_WARNING, JOptionPane.WARNING_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
			return false;
		}
		return true;
	}


	private boolean handlePossiblyRunningFetching() {
		if (caller.isFetchingInProgress()) {
			JOptionPane.showMessageDialog(optionPanel, "Bevor die Verbindung geändert werden kann, muss erst das aktuell laufende Laden der Bilder abgebrochen oder beendet werden.", MediaSearchGUI.DIALOG_WARNING, JOptionPane.WARNING_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
			return false;
		}
		return true;
	}


	private void activateAndSavePerformanceOptions() {
		if (getData(this)) {
			activateActualPerformanceOptions();
			savePerformanceOptionsInUserProfile();
			frame.setVisible(false);
		}
	}


	private void activateAndSaveFavouriteOptions() {
		saveFavouriteOptionsInUserProfile();
		frame.setVisible(false);
	}


	private void activateAndSaveProductivityOptions() {
		activateActualProductivityOptions();
		saveProductivityOptionsInUserProfile();
		frame.setVisible(false);
	}


	private void saveProductivityOptionsInUserProfile() {
		UserSettingsController.getInstance().saveUserSettings(getProductivityOptions());
	}


	private void saveFavouriteOptionsInUserProfile() {
		Map<String, Object> userSettings = new HashMap<String, Object>(2);
		userSettings.put(UserData.SEARCHES_A.getKey(), manager.getAvailableFavouriteSearches());
		userSettings.put(UserData.SEARCHES_V.getKey(), manager.getVisibleFavouriteSearches());
		UserSettingsController.getInstance().saveUserSettings(userSettings);
	}


	private void savePerformanceOptionsInUserProfile() {
		UserSettingsController.getInstance().saveUserSettings(getPerformanceOptions());
	}


	public Map<String, Object> getProductivityOptions() {
		Map<String, Object> userSettings = new HashMap<String, Object>(8);
		userSettings.put(UserData.COMPONENTS.getKey(), ((ExcludedComponentListModel) excludedComponentsList.getModel()).getExcludedComponentNames());
		userSettings.put(UserData.FIRST.getKey(), focusTraversalPolicy.getFirstComponent(centerPanel).getName());
		userSettings.put(UserData.LAST.getKey(), focusTraversalPolicy.getLastComponent(centerPanel).getName());
		userSettings.put(UserData.POSITION_DIALOG.getKey(), galleryPositionCheckBox.isSelected());
		userSettings.put(UserData.ACTIVE.getKey(), activeSearchCheckBox.isSelected());
		userSettings.put(UserData.INACTIVE.getKey(), inactiveSearchCheckBox.isSelected());
		userSettings.put(UserData.AUTOSAVE.getKey(), autoSaveCheckBox.isSelected());
		if (windowPositionCheckBox.isSelected()) {
			userSettings.put(UserData.WINDOWLOC.getKey(), manager.getWindowLocation());
			userSettings.put(UserData.WINDOWSIZE.getKey(), manager.getWindowSize());
		}
		return userSettings;
	}


	public Map<String, Object> getPerformanceOptions() {
		Map<String, Object> userSettings = new HashMap<String, Object>(4);
		userSettings.put(UserData.MEMORY.getKey(), maxMemoryInMB);
		userSettings.put(UserData.RESOLUTION.getKey(), maxPictureResolution);
		userSettings.put(UserData.METHOD.getKey(), scalingMethod);
		userSettings.put(UserData.MODE.getKey(), scalingMode);
		return userSettings;
	}


	private void activateActualProductivityOptions() {
		focusTraversalPolicy.setFirstComponent((String) firstComponentComboBox.getSelectedItem());
		focusTraversalPolicy.setLastComponent((String) lastComponentComboBox.getSelectedItem());
		manager.setPositionDialogPreferred(galleryPositionCheckBox.isSelected());
		manager.setActiveSearchPreferred(activeSearchCheckBox.isSelected());
		manager.setInactiveSearchPreferred(inactiveSearchCheckBox.isSelected());
		manager.setWindowSavingPreferred(windowPositionCheckBox.isSelected());
		manager.setAutoSavePreferred(autoSaveCheckBox.isSelected());
	}


	private void activateActualPerformanceOptions() {
		manager.useMaximalSpaceInMegaByte(maxMemoryInMB);
		manager.usePictureResolution(maxPictureResolution);
		manager.useScalingMethod(scalingMethod);
		manager.useScalingMode(scalingMode);
	}


	public void showOptions() {
		open("Optionen");
	}


	public void hideOptions() {
		frame.setVisible(false);
	}


	private void open(final String title) {
		LOG.info(String.format("The options '%s' will be displayed in a separate window", title));
		if (frame == null) {
			initializeFrame(title);
		}
		fillAvailableSearches();
		fillVisibleSearches();
		fillCheckBoxes();
		frame.center();
		frame.setVisible(true);
	}


	private void fillCheckBoxes() {
		galleryPositionCheckBox.setSelected(manager.isPositionDialogPreferred());
		activeSearchCheckBox.setSelected(manager.isActiveSearchPreferred());
		inactiveSearchCheckBox.setSelected(manager.isInactiveSearchPreferred());
		windowPositionCheckBox.setSelected(manager.isWindowSavingPreferred());
		autoSaveCheckBox.setSelected(manager.isAutoSavePreferred());
	}


	private void fillLists() {
		fillFocusPolicy();
		fillAvailableSearches();
		fillVisibleSearches();
		fillExcludedFocusPolicy();
	}


	private void fillVisibleSearches() {
		FavouriteSearchesListModel visibleSearches = new FavouriteSearchesListModel();
		for (MediaSearch mediaSearch : manager.getVisibleFavouriteSearches()) {
			visibleSearches.addElement(mediaSearch);
		}
		visibleFavouriteSearchesList.setModel(visibleSearches);
		visibleFavouriteSearchesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}


	private void fillAvailableSearches() {
		FavouriteSearchesListModel availableSearches = new FavouriteSearchesListModel();
		for (MediaSearch mediaSearch : manager.getAvailableFavouriteSearches()) {
			availableSearches.addElement(mediaSearch);
		}
		availableFavouriteSearchesList.setModel(availableSearches);
		availableFavouriteSearchesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}


	private void fillFocusPolicy() {
		List<Component> components = focusTraversalPolicy.getComponents();
		List<String> excludedComponentNames = focusTraversalPolicy.getExcludedFromTraversalPolicyComponentNames();
		ExcludedComponentListModel tabbingListModel = new ExcludedComponentListModel();
		for (Component component : components) {
			if (!excludedComponentNames.contains(component.getName())) {
				tabbingListModel.addElement(component.getName());
			}
		}
		availableComponentsList.setModel(tabbingListModel);
		availableComponentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}


	private void fillExcludedFocusPolicy() {
		List<String> excludedComponentNames = focusTraversalPolicy.getExcludedFromTraversalPolicyComponentNames();
		ExcludedComponentListModel excludedComponentListModel = new ExcludedComponentListModel();
		for (String excludedComponentName : excludedComponentNames) {
			excludedComponentListModel.addElement(excludedComponentName);
		}
		excludedComponentsList.setModel(excludedComponentListModel);
		excludedComponentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}


	private void fillLabels() {
		maxMemoryTextField.setText(String.valueOf(manager.getMaximalSpaceInMegaByte()));
		if (connection.isStandAlone()) {
			serverTextField.setText(connection.getServerName());
			portTextField.setText(String.valueOf(connection.getPort()));
			userTextField.setText(connection.getUser());
			passwordField.setText(connection.getPassword());
		}
	}


	private void fillComboBoxes() {
		fillMaxPicture();
		fillScalrMethod();
		fillScalrMode();
		fillTargetSystem();
		fillConnection();
		fillPerformanceLevel();
		fillFirstAndLastComponent();
	}


	private void fillFirstAndLastComponent() {
		List<Component> components = focusTraversalPolicy.getComponents();
		for (Component component : components) {
			if (component.getName() != null) {
				firstComponentComboBox.addItem(component.getName());
				lastComponentComboBox.addItem(component.getName());
			}
		}
		firstComponentComboBox.setSelectedItem(focusTraversalPolicy.getFirstComponent(centerPanel).getName());
		lastComponentComboBox.setSelectedItem(focusTraversalPolicy.getLastComponent(centerPanel).getName());
	}


	private void fillPerformanceLevel() {
		for (PerformanceLevel performanceLevel : PerformanceLevel.values()) {
			predefinedPerformancesComboBox.addItem(performanceLevel);
		}
		predefinedPerformancesComboBox.setSelectedItem(PerformanceLevel.NORMAL_PERFORMANCE_LEVEL);
	}


	private void fillConnection() {
		if (connection.isStandAlone()) {
			for (Environment environment : Environment.values()) {
				predefinedConnectionsComboBox.addItem(environment);
			}
			predefinedConnectionsComboBox.setSelectedItem(Environment.DEV_CON);
		}
	}


	private void fillTargetSystem() {
		if (connection.isStandAlone()) {
			for (TargetSystem targetSystem : TargetSystem.values()) {
				targetSystemComboBox.addItem(targetSystem);
			}
			targetSystemComboBox.setSelectedItem(TargetSystem.FB);
		}
	}


	private void fillScalrMode() {
		for (Scalr.Mode mode : Scalr.Mode.values()) {
			modeComboBox.addItem(mode);
		}
		if (scalingMode != null) {
			modeComboBox.setSelectedItem(scalingMode);
		} else {
			modeComboBox.setSelectedItem(Scalr.Mode.AUTOMATIC);
		}
	}


	private void fillScalrMethod() {
		for (Scalr.Method method : Scalr.Method.values()) {
			methodComboBox.addItem(method);
		}
		if (scalingMethod != null) {
			methodComboBox.setSelectedItem(scalingMethod);
		} else {
			methodComboBox.setSelectedItem(Scalr.Method.AUTOMATIC);
		}
	}


	private void fillMaxPicture() {
		for (Resolution resolution : Resolution.values()) {
			maxPictureComboBox.addItem(resolution);
		}
		if (maxPictureResolution != null) {
			maxPictureComboBox.setSelectedItem(maxPictureResolution);
		} else {
			maxPictureComboBox.setSelectedItem(Resolution.AUTO_DEF);
		}
	}


	private void initializeFrame(final String title) {
		frame = new CenteredFrame(title);
		frame.setContentPane(optionPanel);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}


	public boolean setMaxMemoryInMB(final String maxMemoryInMB) {
		int temp = 0;
		try {
			temp = Integer.parseInt(maxMemoryInMB);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(optionPanel, "Ihre Eingabe der maximalen Speichergröße war leider keine gültige Zahl, bitte wähle Sie eine Zahl in dem Bereich zwischen ca. 50 und 250 MB aus.", MediaSearchGUI.DIALOG_WARNING, JOptionPane.WARNING_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
			return false;
		}
		if (temp > 0) {
			if (temp > 10 && temp <= 512) {
				this.maxMemoryInMB = temp;
				return true;
			} else {
				JOptionPane.showMessageDialog(optionPanel, "Der von Ihnen angegebene Speichergröße ist viel zu klein oder viel zu groß. Bitte wehlen Sie ein eine Zahl aus den Bereich zwischen ca. 50 und 250 MB aus.", MediaSearchGUI.DIALOG_WARNING, JOptionPane.WARNING_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
				return false;
			}
		} else {
			return false;
		}
	}


	public int getPort() {
		int port = 433;
		try {
			port = Integer.parseInt(portTextField.getText());
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(optionPanel, "Ihre Port Eingabe war leider keine gültige Zahl, bitte wähle Sie eine Zahl", "Warnung", JOptionPane.WARNING_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
		}
		return port;
	}


	public boolean getData(final OptionFrame data) {
		if (data.setMaxMemoryInMB(maxMemoryTextField.getText())) {
			data.maxPictureResolution = setMaxPictureResolution((Resolution) maxPictureComboBox.getSelectedItem());
			data.scalingMethod = (Scalr.Method) methodComboBox.getSelectedItem();
			data.scalingMode = (Scalr.Mode) modeComboBox.getSelectedItem();
			return true;
		} else {
			return false;
		}
	}


	public static Resolution setMaxPictureResolution(final Resolution resolution) {
		if (resolution.toString().startsWith("AUTOMATIC")) {
			resolution.setDimension(getAutomaticDimension(resolution));
		} else if (!resolution.isManipulated() && !"ORIGINAL".equals(resolution.toString())) {
			resolution.setDimension(new Dimension(resolution.getWidth() - 10, resolution.getHeight() - 50));
		}
		return resolution;
	}


	private static Dimension getAutomaticDimension(final Resolution resolution) {
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		if (resolution == Resolution.AUTO_MAX) {
			return new Dimension(screenDim.width - 10, screenDim.height - 50);
		} else if (resolution == Resolution.AUTO_DEF) {
			int width = screenDim.width - 10 - screenDim.width / 3;
			int height = screenDim.height - 50 - screenDim.height / 3;
			return new Dimension(width, height);
		} else if (resolution == Resolution.AUTO_MIN) {
			int width = (screenDim.width / 2) - 10;
			int height = (screenDim.height / 2) - 50;
			return new Dimension(width, height);
		} else {
			return screenDim;
		}
	}


	{
		// GUI initializer generated by IntelliJ IDEA GUI Designer
		// >>> IMPORTANT!! <<<
		// DO NOT EDIT OR ADD ANY CODE HERE!
		// $$$setupUI$$$();
	}


	@Override
	public void callBack(final boolean success) {
		if (success) {
			JOptionPane.showMessageDialog(optionPanel, "Verbindung Erfolgreich!", "Information", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
			manager.reactToReconnection();
			frame.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(optionPanel, "Es konnte leider keine Verbindung aufgebaut werden.", "Information", JOptionPane.WARNING_MESSAGE, new ImageIcon(MediaSearchIcon.SEARCH_ICON.getImageData()));
		}
	}


	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		optionPanel = new JPanel();
		optionPanel.setLayout(new BorderLayout(0, 0));
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		optionPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBorder(BorderFactory.createTitledBorder("Einstellungen"));
		optionTabbedPane = new JTabbedPane();
		centerPanel.add(optionTabbedPane, BorderLayout.NORTH);
		performanceTab = new JPanel();
		performanceTab.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
		optionTabbedPane.addTab("Performance", performanceTab);
		memoryPane = new JPanel();
		memoryPane.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
		performanceTab.add(memoryPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		memoryPane.setBorder(BorderFactory.createTitledBorder("Speicherverbrauch"));
		maxMemoryLabel = new JLabel();
		maxMemoryLabel.setText("maximaler Speicher:");
		memoryPane.add(maxMemoryLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		maxMemoryTextField = new JTextField();
		memoryPane.add(maxMemoryTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		maxMemoryLabel2 = new JLabel();
		maxMemoryLabel2.setText("MB");
		maxMemoryLabel2.setToolTipText("Mega Byte");
		memoryPane.add(maxMemoryLabel2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		maxPictureLabel = new JLabel();
		maxPictureLabel.setText("maximale Bildgröße:");
		memoryPane.add(maxPictureLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		maxPictureComboBox = new JComboBox();
		maxPictureComboBox.setEditable(false);
		memoryPane.add(maxPictureComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		scalarPane = new JPanel();
		scalarPane.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
		performanceTab.add(scalarPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		scalarPane.setBorder(BorderFactory.createTitledBorder("Skalierung der Vorschaubilder"));
		methodLabel = new JLabel();
		methodLabel.setText("Skalierungs Methode:");
		scalarPane.add(methodLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		methodComboBox = new JComboBox();
		methodComboBox.setEditable(false);
		scalarPane.add(methodComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		modeLabel = new JLabel();
		modeLabel.setText("Skalierungs Modus");
		scalarPane.add(modeLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		modeComboBox = new JComboBox();
		modeComboBox.setEditable(false);
		scalarPane.add(modeComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		controllPane = new JPanel();
		controllPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		performanceTab.add(controllPane, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(109, 21), null, 0, false));
		saveButton = new JButton();
		saveButton.setText("Übernehmen und Speichern");
		saveButton.setToolTipText("Performance Einstellungen Übernehmen und Speichern");
		controllPane.add(saveButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(85, 24), null, 0, false));
		final Spacer spacer1 = new Spacer();
		controllPane.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(14, 14), null, 0, false));
		predefinedPerformancesComboBox = new JComboBox();
		predefinedPerformancesComboBox.setEditable(false);
		performanceTab.add(predefinedPerformancesComboBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer2 = new Spacer();
		performanceTab.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		favouriteSearchesTab = new JPanel();
		favouriteSearchesTab.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
		optionTabbedPane.addTab("Favoriten", favouriteSearchesTab);
		favouriteSearchesTab.setBorder(BorderFactory.createTitledBorder("Liste der gespeicherten Such Aufgträge"));
		favouriteSearchesListScrollPane1 = new JScrollPane();
		favouriteSearchesTab.add(favouriteSearchesListScrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		favouriteSearchesListScrollPane1.setBorder(BorderFactory.createTitledBorder("Vorhandene Favoriten"));
		availableFavouriteSearchesList = new JList();
		availableFavouriteSearchesList.setDragEnabled(false);
		availableFavouriteSearchesList.setDropMode(DropMode.USE_SELECTION);
		availableFavouriteSearchesList.setFixedCellWidth(210);
		availableFavouriteSearchesList.setSelectionMode(2);
		favouriteSearchesListScrollPane1.setViewportView(availableFavouriteSearchesList);
		favouriteSearchesListScrollPane2 = new JScrollPane();
		favouriteSearchesTab.add(favouriteSearchesListScrollPane2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		favouriteSearchesListScrollPane2.setBorder(BorderFactory.createTitledBorder("Ausgewählte Favoriten (max 6)"));
		visibleFavouriteSearchesList = new JList();
		visibleFavouriteSearchesList.setDragEnabled(false);
		visibleFavouriteSearchesList.setDropMode(DropMode.USE_SELECTION);
		visibleFavouriteSearchesList.setFixedCellWidth(210);
		visibleFavouriteSearchesList.setSelectionMode(2);
		favouriteSearchesListScrollPane2.setViewportView(visibleFavouriteSearchesList);
		favouriteControllPane = new JPanel();
		favouriteControllPane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		favouriteSearchesTab.add(favouriteControllPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		favouriteAddButton = new JButton();
		favouriteAddButton.setText(">");
		favouriteControllPane.add(favouriteAddButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		favouriteRemoveButton = new JButton();
		favouriteRemoveButton.setText("<");
		favouriteControllPane.add(favouriteRemoveButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		favouriteControllPane2 = new JPanel();
		favouriteControllPane2.setLayout(new BorderLayout(0, 0));
		favouriteSearchesTab.add(favouriteControllPane2, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		favouriteSaveButton = new JButton();
		favouriteSaveButton.setText("Speichern");
		favouriteSaveButton.setToolTipText("Favorisierte Suchen Speichern");
		favouriteControllPane2.add(favouriteSaveButton, BorderLayout.EAST);
		productivityTyp = new JPanel();
		productivityTyp.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
		optionTabbedPane.addTab("Produktivität", productivityTyp);
		productivityTyp.setBorder(BorderFactory.createTitledBorder("Liste der Elemente, welche nacheinander den Fokus beim Tabbing erhalten"));
		tabbingListScrollPanel1 = new JScrollPane();
		productivityTyp.add(tabbingListScrollPanel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		tabbingListScrollPanel1.setBorder(BorderFactory.createTitledBorder("Mögliche Komponenten"));
		availableComponentsList = new JList();
		availableComponentsList.setDragEnabled(false);
		availableComponentsList.setDropMode(DropMode.USE_SELECTION);
		availableComponentsList.setFixedCellHeight(-1);
		availableComponentsList.setFixedCellWidth(210);
		availableComponentsList.setSelectionMode(0);
		tabbingListScrollPanel1.setViewportView(availableComponentsList);
		tabbingListScrollPanel2 = new JScrollPane();
		productivityTyp.add(tabbingListScrollPanel2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		tabbingListScrollPanel2.setBorder(BorderFactory.createTitledBorder("Ausgeschlossene Komponenten"));
		excludedComponentsList = new JList();
		excludedComponentsList.setDragEnabled(false);
		excludedComponentsList.setDropMode(DropMode.USE_SELECTION);
		excludedComponentsList.setFixedCellWidth(210);
		excludedComponentsList.setSelectionMode(0);
		tabbingListScrollPanel2.setViewportView(excludedComponentsList);
		tabbingControllPanel = new JPanel();
		tabbingControllPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		productivityTyp.add(tabbingControllPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		tabbingAddButton = new JButton();
		tabbingAddButton.setText(">");
		tabbingControllPanel.add(tabbingAddButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		tabbingRemoveButton = new JButton();
		tabbingRemoveButton.setText("<");
		tabbingControllPanel.add(tabbingRemoveButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		tabbingControllPanel2 = new JPanel();
		tabbingControllPanel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		productivityTyp.add(tabbingControllPanel2, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		tabbingSaveButton = new JButton();
		tabbingSaveButton.setText("Übernehmen und Speichern");
		tabbingSaveButton.setToolTipText("Einstellungen zur Produktivität Übernehmen und Speichern");
		tabbingControllPanel2.add(tabbingSaveButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final Spacer spacer3 = new Spacer();
		tabbingControllPanel2.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		productivityPanel = new JPanel();
		productivityPanel.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
		productivityTyp.add(productivityPanel, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		firstComponentLabel = new JLabel();
		firstComponentLabel.setText("erste Komponente");
		productivityPanel.add(firstComponentLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		firstComponentComboBox = new JComboBox();
		firstComponentComboBox.setEditable(false);
		productivityPanel.add(firstComponentComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		lastComponentLabel = new JLabel();
		lastComponentLabel.setText("letzte Komponente");
		productivityPanel.add(lastComponentLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		lastComponentComboBox = new JComboBox();
		lastComponentComboBox.setEditable(false);
		productivityPanel.add(lastComponentComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		activeSearchLabel = new JLabel();
		activeSearchLabel.setText("Startvorgabe für");
		productivityPanel.add(activeSearchLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		activeSearchCheckBox = new JCheckBox();
		activeSearchCheckBox.setText("nur aktive Medien");
		productivityPanel.add(activeSearchCheckBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		autoSaveLabel = new JLabel();
		autoSaveLabel.setText("Automatisches Speichern");
		productivityPanel.add(autoSaveLabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		autoSaveCheckBox = new JCheckBox();
		autoSaveCheckBox.setText("");
		productivityPanel.add(autoSaveCheckBox, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		inactiveSearchLabel = new JLabel();
		inactiveSearchLabel.setText("Startvorgabe für");
		productivityPanel.add(inactiveSearchLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		inactiveSearchCheckBox = new JCheckBox();
		inactiveSearchCheckBox.setText("nur inaktive Medien");
		productivityPanel.add(inactiveSearchCheckBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		windowPositionLabel = new JLabel();
		windowPositionLabel.setText("Fensterposition Speichern");
		productivityPanel.add(windowPositionLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		windowPositionCheckBox = new JCheckBox();
		windowPositionCheckBox.setText("");
		productivityPanel.add(windowPositionCheckBox, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		galleryPositionLabel = new JLabel();
		galleryPositionLabel.setText("Galerie Position vor dem Einfügen abfragen");
		productivityPanel.add(galleryPositionLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		galleryPositionCheckBox = new JCheckBox();
		galleryPositionCheckBox.setText("");
		productivityPanel.add(galleryPositionCheckBox, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		connectionTab = new JPanel();
		connectionTab.setLayout(new GridLayoutManager(9, 4, new Insets(0, 0, 0, 0), -1, -1));
		optionTabbedPane.addTab("Verbindungen", connectionTab);
		connectionTab.setBorder(BorderFactory.createTitledBorder("Verbindungen"));
		final Spacer spacer4 = new Spacer();
		connectionTab.add(spacer4, new GridConstraints(6, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		serverLabel = new JLabel();
		serverLabel.setText("Server");
		connectionTab.add(serverLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		projectLabel = new JLabel();
		projectLabel.setText("Projekt");
		connectionTab.add(projectLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		userLabel = new JLabel();
		userLabel.setText("Benutzer");
		connectionTab.add(userLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		userTextField = new JTextField();
		connectionTab.add(userTextField, new GridConstraints(4, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		passwordLabel = new JLabel();
		passwordLabel.setText("Passwort");
		connectionTab.add(passwordLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		portLabel = new JLabel();
		portLabel.setText("Port");
		connectionTab.add(portLabel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		portTextField = new JTextField();
		connectionTab.add(portTextField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), null, 0, false));
		passwordField = new JPasswordField();
		connectionTab.add(passwordField, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		final Spacer spacer5 = new Spacer();
		connectionTab.add(spacer5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		connectionButton = new JButton();
		connectionButton.setText("Verbinden");
		connectionTab.add(connectionButton, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		serverTextField = new JTextField();
		serverTextField.setEditable(true);
		connectionTab.add(serverTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		projectTextField = new JTextField();
		projectTextField.setEditable(true);
		connectionTab.add(projectTextField, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
		predefinedConnectionsComboBox = new JComboBox();
		predefinedConnectionsComboBox.setEditable(false);
		connectionTab.add(predefinedConnectionsComboBox, new GridConstraints(8, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		predefinedConnectionsLabel = new JLabel();
		predefinedConnectionsLabel.setText("Vordefinierte");
		connectionTab.add(predefinedConnectionsLabel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		targetSystemLabel = new JLabel();
		targetSystemLabel.setText("Ziel System");
		connectionTab.add(targetSystemLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		targetSystemComboBox = new JComboBox();
		targetSystemComboBox.setEditable(false);
		connectionTab.add(targetSystemComboBox, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer6 = new Spacer();
		connectionTab.add(spacer6, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
	}


	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() { return optionPanel; }
}
