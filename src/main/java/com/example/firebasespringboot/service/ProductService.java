package com.example.firebasespringboot.service;

import com.example.firebasespringboot.entity.Person;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {
    private static final String COLLECTION_NAME = "person";

    public String savePerson(Person person) throws Exception{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture= dbFirestore.collection(COLLECTION_NAME).document(person.getName()).set(person);
       return collectionApiFuture.get().getUpdateTime().toString();
    }

public Person getPerson(String name) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
    DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);
    ApiFuture<DocumentSnapshot> future = documentReference.get();
    DocumentSnapshot document = future.get();
    Person person = null;
    if(document.exists()) {
        person = document.toObject(Person.class);
        return person;
    }
    else {
        return null;
    }
}
    public List<Person> getAllPersons() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> future = collectionReference.get();

        List<Person> persons = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            Person person = document.toObject(Person.class);
            persons.add(person);
        }

        return persons;
    }
}
