http://localhost:8080/api/swagger-ui/index.html


Création d'un dossier (POST /api/folders)
{
"name": "Procédures RH",
"description": "Documents relatifs aux procédures des ressources humaines",
"parentId": null
}

// Pour créer un sous-dossier
{
"name": "Recrutement",
"description": "Procédures de recrutement",
"parentId": 1
}


Mise à jour d'un dossier (PUT /api/folders/{id})

{
"name": "Procédures RH 2024",
"description": "Documents RH mis à jour pour 2024",
"parentId": 1
}


Création d'un document (POST /api/documents)

{
"id": 1,
"name": "Plan du projet",
"description": "Plan détaillé du projet incluant les phases et ressources nécessaires.",
"version": "1.0",
"fileType": "pdf",
"fileSize": 1048576,
"filePath": "/documents/projets/plan_du_projet_v1.pdf",
"status": "ACTIVE",
"folderId": 2,
"parentDocumentId": null,
"versionIds": [2, 3],
"tags": ["projet", "planification", "gestion"],
"metadata": {
"auteur": "Jane Doe",
"département": "Gestion de projet",
"confidentialité": "Interne"
},
"createdAt": "2024-03-01T10:00:00",
"updatedAt": "2024-03-02T15:30:00"
}



}

// Autre exemple



{
"id": 2,
"name": "Plan du projet",
"description": "Plan mis à jour avec modifications selon feedback du comité.",
"version": "1.1",
"fileType": "pdf",
"fileSize": 2097152,
"filePath": "/documents/projets/plan_du_projet_v1_1.pdf",
"status": "ACTIVE",
"folderId": 2,
"parentDocumentId": 1,
"versionIds": [],
"tags": ["projet", "mise à jour", "validation"],
"metadata": {
"auteur": "John Smith",
"département": "Gestion de projet",
"confidentialité": "Interne",
"approbation": "Oui"
},
"createdAt": "2024-03-10T12:00:00",
"updatedAt": "2024-03-11T09:15:00"
}


Mise à jour d'un document (PUT /api/documents/{id})
{
"name": "Guide Onboarding",
"description": "Guide d'intégration des nouveaux employés - Mise à jour 2024",
"version": "1.1",
"fileType": "PDF",
"fileSize": 2048,
"folderId": 3,
"tags": ["onboarding", "rh", "procédure", "2024"],
"metadata": {
"author": "Service RH",
"department": "Ressources Humaines",
"lastReviewer": "Marie Dupont",
"lastUpdateDate": "2024-03-22"
}
}
