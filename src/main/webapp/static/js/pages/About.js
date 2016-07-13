import React from "react";

export default class About extends React.Component {
    render() {
        return (
            <div>
                <h1>Codename Paris</h1>
                <p>Projekt Codename Paris je izrađen u sklopu kolegija Napredno programiranje u Java programsko jeziku na visokoškolskoj ustanovi Tehničko Veleučilište u Zagrebu kod v.pred Aleksander Radovan, dipl.ing.
                Projekt Codename Paris sastoji se od 3 ključne komponente: serverskog dijela odnosno API-ja s bazom podataka, mobilne Android aplikacije i web aplikacije izrađene s ReactJS koje komuniciraju s
                API-em. Cijeli projekt simulira sustav za kreiranje događaja te prodaju i kupovinu karata za te događaje.</p>
                <hr/>
                <h3>U razvoju su korištene slijedeće tehnologije:</h3>
                <ul>
                    <li>Java Spring</li>
                    <li>ReactJS</li>
                    <li>...</li>
                </ul>
                <hr/>
                <h4>Članovi tima koji su radili na projektu:</h4>
                <ul>
                    <li>Josip Kovaček</li>
                    <li>Luka Mitak</li>
                    <li>Filip Vinković</li>
                    <li>Filip Ćorluka</li>
                    <li>Josip Perić</li>
                    <li>Marijan Pezo</li>
                    <li>Denis Glad</li>
                </ul>
            </div>
        );
    }
}
