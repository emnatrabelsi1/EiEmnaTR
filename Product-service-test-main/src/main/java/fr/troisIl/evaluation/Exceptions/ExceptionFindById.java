/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.troisIl.evaluation.Exceptions;

/**
 *
 * @author emnat
 */
public class ExceptionFindById extends RuntimeException {
     public ExceptionFindById() {
        super();
    }
    public ExceptionFindById(String message) {
        super(message);
    }}
