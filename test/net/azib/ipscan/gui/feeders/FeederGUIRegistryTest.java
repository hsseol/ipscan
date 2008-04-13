/**
 * This file is a part of Angry IP Scanner source code,
 * see http://www.azib.net/ for more information.
 * Licensed under GPLv2.
 */
package net.azib.ipscan.gui.feeders;

import net.azib.ipscan.feeders.Feeder;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.classextension.EasyMock.*;

/**
 * FeederGUIRegistryTest
 *
 * @author Anton Keks
 */
public class FeederGUIRegistryTest {
	
	private Composite parent;
	private FeederGUIRegistry registry;
	
	@Before
	public void createRegistry() {
		parent = new Shell();
		registry = new FeederGUIRegistry(new AbstractFeederGUI[] {new RangeFeederGUI(parent)}, null, null);
	}
	
	@After
	public void dispose() {
		parent.dispose();
	}

	@Test
	public void createFeederRemembersTheLastOne() throws Exception {
		Feeder lastFeeder = registry.createFeeder();
		assertSame(lastFeeder, registry.lastScanFeeder);
		assertNotSame(lastFeeder, registry.createFeeder());
	}

	@Test
	public void createRescanFeederGetsOriginalFeeder() throws Exception {
		Feeder lastFeeder = registry.createFeeder();
		Feeder rescanFeeder = registry.createRescanFeeder(new TableItem[] {createMock(TableItem.class)});
		assertEquals(lastFeeder.getId(), rescanFeeder.getId());
	}
}