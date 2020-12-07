package io.github.oliviercailloux.mido_svg.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import io.github.oliviercailloux.mido_svg.xml.jaxb.model.Tag;
import io.github.oliviercailloux.mido_svg.xml.jaxb.model.TagStore;

/**
 * This class is used to use JAXB
 *
 * @author Cocolollipop
 *
 */
public class ControllerJAXB {
	public static final String PATH_TAGS = "./src/main/resources/tags/";

	/**
	 * This subroutine is used to create an XML file from tags
	 *
	 * @throws JAXBException
	 */
	public void createTagsFileXML(String username) throws JAXBException {
		// Map tags, String user
		/**
		 * We know that the list of available subjects contains: proba, java, logique
		 */
		List<Tag> TagList = new ArrayList<>();

		// create tag1 for subjects: info, proba, java
		Tag tag1 = new Tag();
		tag1.setName("info");
		tag1.addSubject("proba");
		tag1.addSubject("java");
		TagList.add(tag1);

		// create tag2 for subjects: logique, java, proba
		Tag tag2 = new Tag();
		tag2.setName("maths");
		tag2.addSubject("logique");
		tag2.addSubject("java");
		tag2.addSubject("proba");
		TagList.add(tag2);

		// create tagstore
		TagStore tagstore = new TagStore();
		tagstore.setName("TagStore");
		tagstore.setTagList(TagList);

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(TagStore.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(tagstore, System.out);

		// Write to File
		m.marshal(tagstore, new File(PATH_TAGS + username + "-tagstore-jaxb.xml"));

	}

	public void createTagsFileXML(String username, List<Tag> userListOfTags) throws JAXBException {
		// Map tags, String user
		/**
		 * We know that the list of available subjects contains: proba, java, logique
		 */

		// create tagstore
		TagStore tagstore = new TagStore();
		tagstore.setName("TagStore");
		tagstore.setTagList(userListOfTags);

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(TagStore.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(tagstore, System.out);

		// Write to File
		m.marshal(tagstore, new File(PATH_TAGS + username + "-tagstore-jaxb.xml"));

	}

	/**
	 * This subroutine creates tags from an XML file
	 *
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public List<Tag> readTagsFileXML(String username) throws JAXBException, IOException {
		System.out.println("Output from our XML File: ");
		JAXBContext context = JAXBContext.newInstance(TagStore.class);
		Unmarshaller um = context.createUnmarshaller();
		/* Creates the tagStore */
		File file = new File(PATH_TAGS + username + "-tagstore-jaxb.xml");
		if (file.exists() && !file.isDirectory()) {
			TagStore tagstore2 = (TagStore) um.unmarshal(new FileReader(PATH_TAGS + username + "-tagstore-jaxb.xml"));
			List<Tag> list = tagstore2.getTagsList();
			/**
			 * The print so you can understand the results
			 */
			for (Tag tag : list) {
				System.out.println("Tag: " + tag.getName() + tag.getSubjects());
			}

			return list;
		} else {
			this.createTagsFileXML(username);
			this.readTagsFileXML(username);

		}
		return null;

	}
}
