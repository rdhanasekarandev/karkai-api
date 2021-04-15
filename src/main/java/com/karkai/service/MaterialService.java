package com.karkai.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.karkai.modal.Material;
import com.karkai.modal.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class MaterialService {

    // get all materials
    public List<Material> getAllMaterial()throws ExecutionException, InterruptedException{
        Firestore dbFireStore= FirestoreClient.getFirestore();
        List<Material> materials=new ArrayList<>();

        ApiFuture<QuerySnapshot> future = dbFireStore.collection("Materials").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        for (QueryDocumentSnapshot queryDocumentSnapshot:documentSnapshots){
            materials.add(queryDocumentSnapshot.toObject(Material.class));
        }
        return materials;
    }

    // get subject material
    public List<Material> getMaterialBySubject(String subject,String exam) throws ExecutionException, InterruptedException {
        Firestore dbFireStore= FirestoreClient.getFirestore();
        List<Material> materials=new ArrayList<>();

        ApiFuture<QuerySnapshot> future = dbFireStore.collection("Materials").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        for (QueryDocumentSnapshot queryDocumentSnapshot:documentSnapshots){
            Material material = queryDocumentSnapshot.toObject(Material.class);
            if(material.getSubject().equals(subject)) {
                if(material.getExam().equals(exam))
                materials.add(queryDocumentSnapshot.toObject(Material.class));
            }
        }
        return materials;
    }

    // add new material in firebase
    public String addMaterial(Material material) {
        Firestore firestore= FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture=firestore.collection("Materials").document(material.getSubject()+material.getName()).set(material);
        return "success";
    }

}
