package com.spring.vaadin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.dao.CategoryDao;
import com.spring.dao.ItemDao;
import com.spring.model.Collection;
import com.spring.model.Item;

import com.spring.model.Category;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.validator.StringLengthValidator;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;

import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class ItemEditor extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Item item;
	TextField name = new TextField("Name");
	TextField brandname = new TextField("Brand name");
	TextField color = new TextField("Color");
	TextField shape = new TextField("Shape");
	TextArea info = new TextArea("Info");
	TextField price = new TextField("Price");
	TextField number = new TextField("Number");
	List<Collection> collectionsData;
	ComboBox<Category> category = new ComboBox<>("Category");
	CheckBoxGroup<Collection> collections = new CheckBoxGroup<Collection>("Collections");
	LineBreakCounter lineBreakCounter;
	Upload upload1 = new Upload();
	Upload upload2 = new Upload();
	Upload upload3 = new Upload();
	Upload upload4 = new Upload();
	Upload upload5 = new Upload();
	Button save = new Button("Save");
	Button delete = new Button("Delete");
	Button cancel = new Button("Cancel");
	Layout parentContent = new VerticalLayout();
	Layout panelContent = new VerticalLayout();
	Panel panel = new Panel("Image Upload");;
	List<Upload> uploads = new ArrayList<Upload>();
	Notification noti = new Notification("This is notification");
	Button uploadmore = new Button("More");
	int imageId = 0;
	Binder<Item> binder = new Binder<>(Item.class);;

	@SuppressWarnings("static-access")
	@Autowired
	public ItemEditor(ItemDao itemDao, CategoryDao categoryDao) {
		// this.item = new Item("", "", "", "", "", "", 0, 0,
		// categoryDao.getAllCategories().get(0));
		List<Category> categoryData = categoryDao.getAllCategories();
		collectionsData = itemDao.getAllCollections();
		// this.collectionsData = new HashSet<Collection>(ls);
		noti.setDelayMsec(2000);
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		GridLayout editorlayout = new GridLayout();
		horizontalLayout.addComponentsAndExpand(save, delete, cancel);
		horizontalLayout.setWidth(40, Unit.PERCENTAGE);
		editorlayout.setSizeFull();
		editorlayout.setRows(3);
		editorlayout.setColumns(4);
		editorlayout.addComponent(name);
		editorlayout.addComponent(brandname);
		editorlayout.addComponent(color);
		editorlayout.addComponent(shape);
		editorlayout.addComponent(info);
		editorlayout.addComponent(price);
		editorlayout.addComponent(number);
		editorlayout.addComponent(category);

		category.setItems(categoryData);
		category.setItemCaptionGenerator(p -> p.getName());
		collections.setItems(collectionsData);
		// collections.setRows(collectionsData.size());
		// collections.setLeftColumnCaption("Available Collections");
		// collections.setRightColumnCaption("Selected Collections");
		collections.setItemCaptionGenerator(p -> p.getName());
		collections.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
		parentContent.addComponents(panelContent, uploadmore);
		panel.setContent(parentContent);

		VerticalLayout editor2layout = new VerticalLayout();
		editor2layout.addComponent(collections);
		editor2layout.addComponent(panel);
		editor2layout.setComponentAlignment(collections, Alignment.TOP_LEFT);
		editor2layout.setComponentAlignment(panel, Alignment.TOP_LEFT);
		editor2layout.setWidth(50, Unit.PERCENTAGE);

		this.addComponents(horizontalLayout, editorlayout, editor2layout);
		save.addClickListener(clickEvent ->

		{
			BinderValidationStatus<Item> status = binder.validate();
			if (!status.hasErrors()) {
				if (item.getItem_id() != null) {
					int aft = itemDao.updateItem(item);

					if (aft == 1) {

						uploadFile();
						noti.show("Edit Item Success", Notification.Type.TRAY_NOTIFICATION);

					} else {
						noti.show("Edit Item Failed", Notification.Type.ERROR_MESSAGE);
					}
				} else {
					int aft = itemDao.insertItem(item);
					if (aft == 1) {
						int lastId = itemDao.getLastIdInserted();
						item = itemDao.getItemsById(lastId);
						for (int i = 0; i < uploads.size(); i++) {

							Upload upload = uploads.get(i);
							if (upload.getReceiver() != null) {
								imageId = imageId + 1;
								LineBreakCounter lineBreakCounter = new LineBreakCounter(
										"src/main/resources/static/images/" + item.getImages() + "-" + imageId
												+ ".jpg");
								upload.setReceiver(lineBreakCounter);
							}
						}
						uploadFile();
						noti.show("Add Item Success", Notification.Type.TRAY_NOTIFICATION);

					} else {
						noti.show("Add Item Failed", Notification.Type.ERROR_MESSAGE);
					}
				}
			} else {
				noti.show("Some fields is not valid!", Notification.Type.WARNING_MESSAGE);
			}
		});

		delete.addClickListener(e -> {
			int aft = itemDao.deleteItem(item);
			if (aft == 1) {
				noti.show("Delete Item Success", Notification.Type.TRAY_NOTIFICATION);
				this.setVisible(false);
			} else {
				noti.show("Delete Item Failed", Notification.Type.ERROR_MESSAGE);
			}

		});
		cancel.addClickListener(clickEvent -> this.setVisible(false));
		uploadmore.addClickListener(e -> addNewUpload());
		setVisible(false);
	}

	private void uploadFile() {
		boolean showUpWindow = false;
		if (uploads.size() > 0) {
			for (int i = 0; i < uploads.size(); i++) {
				Upload upload = uploads.get(i);
				if (upload.getReceiver() != null) {
					showUpWindow = true;
					break;
				}
			}
			if (showUpWindow) {
				UploadInfoWindow uploadInfoWindow = new UploadInfoWindow(uploads);
				if (uploadInfoWindow.getParent() == null) {
					UI.getCurrent().addWindow(uploadInfoWindow);
				}
				uploadInfoWindow.setClosable(true);
			}
		}

		for (int i = 0; i < uploads.size(); i++) {
			Upload upload = uploads.get(i);
			if (upload.getReceiver() != null) {
				upload.submitUpload();
			}
		}
		if (uploads.size() == 1 && uploads.get(0).getReceiver() == null) {
			this.setVisible(false);
		}
		uploads.get(uploads.size() - 1).addFinishedListener(e -> {
			this.setVisible(false);
		});

	}

	private void addNewUpload() {
		Upload upload = new Upload();
		upload.setWidth(100, Unit.PERCENTAGE);
		upload.addChangeListener(e -> {
			if (item.getItem_id() != null) {
				imageId = imageId + 1;
				LineBreakCounter lineBreakCounter = new LineBreakCounter(
						"src/main/resources/static/images/" + item.getImages() + "-" + imageId + ".jpg");
				upload.setReceiver(lineBreakCounter);
			} else {
				upload.setReceiver(new LineBreakCounter("src/main/resources/static/images/junk.jpg"));
			}
		});
		upload.setButtonCaption(null);
		upload.setImmediateMode(false);
		uploads.add(upload);
		panelContent.addComponents(upload);
	}

	private void init() {
		imageId = 0;
		File folder = new File("src/main/resources/static/images");
		File[] listOfFiles = folder.listFiles();
		List<Integer> filteredList = new ArrayList<Integer>();
		for (File file : listOfFiles) {
			// Be aware that folder.listFiles() give list with directories
			// and
			// files
			if (file.isFile()) {
				if (file.getName().toLowerCase().startsWith(item.getImages() + "-")) {
					String s = file.getName().split("-")[1];
					String s2 = s.split("\\.")[0];
					Integer i = Integer.valueOf(s2);
					filteredList.add(i);
				}
			}
		}
		int max = 0;
		if (!filteredList.isEmpty()) {
			for (Integer i : filteredList) {
				if (i > max)
					;
				max = i;
			}
		}
		imageId = max;

		uploads.clear();

		panelContent.removeAllComponents();
		addNewUpload();

		binder.setBean(item);
		binder.forField(name).withValidator(new StringLengthValidator("Size from 1-45", 1, 45)).bind(Item::getName,
				Item::setName);

		binder.forField(brandname).withValidator(new StringLengthValidator("Size from 1-30", 1, 30))
				.bind(Item::getBrandname, Item::setBrandname);

		binder.forField(color).withValidator(new StringLengthValidator("Size from 1-30", 1, 30)).bind(Item::getColor,
				Item::setColor);

		binder.forField(shape).withValidator(new StringLengthValidator("Size from 1-30", 1, 30)).bind(Item::getShape,
				Item::setShape);

		binder.forField(info).withValidator(new StringLengthValidator("Size from 1-200", 1, 200)).bind(Item::getInfo,
				Item::setInfo);

		binder.forField(price).withConverter(Integer::parseInt, Object::toString).bind(Item::getPrice, Item::setPrice);

		binder.forField(number).withConverter(Integer::parseInt, Object::toString).bind(Item::getNumber,
				Item::setNumber);
		binder.forField(category).asRequired("Must choose").bind(Item::getCategory, Item::setCategory);
		binder.forField(collections).bind(Item::getSetCollections, Item::setSetCollections);

		if (item.getCollections() != null && !item.getCollections().isEmpty()) {
			Iterator<Collection> itr1 = item.getCollections().iterator();
			// collections.setValue(collectionsData);
			collections.deselectAll();
			while (itr1.hasNext()) {
				Collection obj1 = itr1.next();
				Iterator<Collection> itr2 = collectionsData.iterator();
				while (itr2.hasNext()) {
					Collection obj2 = itr2.next();
					if (obj1.getCollection_id() == obj2.getCollection_id()) {
						collections.select(obj2);
					}
				}
			}
		}
	}

	public final void editItem(Item i) {
		this.item = i;
		setVisible(true);
		init();
		save.focus();

	}

	public void setChangeHandler(ChangeHandler h) {
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());

	}

	public interface ChangeHandler {
		void onChange();
	}

	@StyleSheet("uploadexample.css")
	private static class UploadInfoWindow extends Window implements Upload.StartedListener, Upload.ProgressListener,
			Upload.FailedListener, Upload.SucceededListener, Upload.FinishedListener {
		/**
		 * 
		 */
		private int numberFailed = 0;
		private static final long serialVersionUID = 4275228350496092163L;
		private final Label state = new Label();
		private final Label fileNames = new Label();
		private long length = 0;
		private long readbytes = 0;
		private final ProgressBar progressBar = new ProgressBar();
		private final VerticalLayout progressLayout = new VerticalLayout();
		private final Label textualProgress = new Label();

		private final Button cancelButton;

		private UploadInfoWindow(final List<Upload> uploads) {
			super("Status");

			addStyleName("upload-info");

			setResizable(false);
			setDraggable(false);

			final FormLayout uploadInfoLayout = new FormLayout();
			setContent(uploadInfoLayout);
			uploadInfoLayout.setMargin(true);

			final HorizontalLayout stateLayout = new HorizontalLayout();
			stateLayout.setSpacing(true);
			stateLayout.addComponent(state);

			cancelButton = new Button("Cancel");
			cancelButton.addClickListener(event -> {
				for (Upload upload : uploads) {
					upload.interruptUpload();
				}
			});
			cancelButton.setVisible(false);
			cancelButton.setStyleName("small");
			stateLayout.addComponent(cancelButton);

			stateLayout.setCaption("Current state");
			state.setValue("Idle");
			uploadInfoLayout.addComponent(stateLayout);

			fileNames.setCaption("File names");
			uploadInfoLayout.addComponent(fileNames);

			progressLayout.setCaption("Progress");
			progressLayout.addComponent(progressBar);
			progressLayout.addComponent(textualProgress);
			uploadInfoLayout.addComponent(progressLayout);
			for (Upload upload : uploads) {
				upload.addStartedListener(this);
				upload.addProgressListener(this);
				upload.addFailedListener(this);
				upload.addSucceededListener(this);
				upload.addFinishedListener(this);
			}

		}

		@Override
		public void updateProgress(final long readBytes, final long contentLength) {
			// this method gets called several times during the update
			this.readbytes = this.readbytes + readBytes;
			progressBar.setValue(this.readbytes / this.length);
			textualProgress.setValue("Processed " + this.readbytes + " bytes of " + this.length);

		}

		@Override
		public void uploadSucceeded(final SucceededEvent event) {

		}

		@Override
		public void uploadFailed(final FailedEvent event) {
			numberFailed = numberFailed + 1;
			state.setValue("Upload Failed counting interrupted at some image");
		}

		@Override
		public void uploadFinished(final FinishedEvent event) {
			state.setValue("Finished with " + numberFailed + " image failed");
			cancelButton.setVisible(false);
		}

		@Override
		public void uploadStarted(final StartedEvent event) {
			// this method gets called immediately after upload is started
			UI.getCurrent().setPollInterval(500);
			// updates to client
			state.setValue("Uploading");
			length = length + event.getContentLength();
			fileNames.setValue(fileNames.getValue() + event.getFilename() + "; ");
			cancelButton.setVisible(true);
		}
	}

	private static class LineBreakCounter implements Receiver {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6307572902282857348L;

		private String path;
		private File file;

		public LineBreakCounter(String path) {

			this.path = path;
		}

		/**
		 * return an OutputStream that simply counts lineends
		 */
		@Override
		public OutputStream receiveUpload(final String filename, final String MIMEType) {
			file = new File(path);
			try {
				return new FileOutputStream(file) {

					@Override
					public void write(final int b) {

					}
				};
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	}

}
