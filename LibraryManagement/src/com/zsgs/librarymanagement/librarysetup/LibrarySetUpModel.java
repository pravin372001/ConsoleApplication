package com.zsgs.librarymanagement.librarysetup;

import com.zsgs.librarymanagement.Librarydatabase.LibraryDatabase;
import com.zsgs.librarymanagement.model.Library;

class LibrarySetUpModel {
    private LibrarySetUpView librarySetUpView;
    private Library library;

    LibrarySetUpModel(LibrarySetUpView librarySetUpView) {
        this.librarySetUpView = librarySetUpView;
    }

    public void startSetup() {
        if (library == null || library.getLibraryId() == 0) {
            librarySetUpView.initiateSetup();
        } else {
            librarySetUpView.onSetupComplete();
        }
    }

    public void saveLibraryDetails(Library library) {
        LibraryDatabase.getInstance().setLibrary(library);
        serializeLibraryDetails(library);
    }

    private void serializeLibraryDetails(Library library) {
        LibraryDatabase.getInstance().serializeLibrary(library);
        librarySetUpView.showAlert("Library Details Saved Successfully");
    }
}
