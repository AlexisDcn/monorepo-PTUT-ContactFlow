import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyAF1lYqlOFBtE1eKmDczMljgOFf21pg5ow",
  authDomain: "contact-flow-835f8.firebaseapp.com",
  projectId: "contact-flow-835f8",
  storageBucket: "contact-flow-835f8.firebasestorage.app",
  messagingSenderId: "212329104781",
  appId: "1:212329104781:web:671cca33e384d5480de9c4",
  measurementId: "G-4QBNMMCF05"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

export { auth };
