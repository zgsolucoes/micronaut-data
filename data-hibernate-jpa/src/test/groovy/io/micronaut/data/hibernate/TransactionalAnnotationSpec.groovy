/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.data.hibernate

import io.micronaut.data.tck.entities.Book
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.transaction.TransactionOperations
import org.hibernate.Session
import spock.lang.Specification

import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional

@MicronautTest(packages = "io.micronaut.data.tck.entities", transactional = false)
@H2DBProperties
class TransactionalAnnotationSpec extends Specification {

    @Inject BookService bookService

    @Inject TransactionOperations<Session> transactionOperations

    void "test transactional annotation"() {
        when:
        bookService.saveAndSuccess()

        then:
        bookService.listBooks().size() == 1
        transactionOperations.findTransactionStatus().isEmpty()

        when:
        bookService.saveAndError()

        then:
        thrown(RuntimeException)
        bookService.listBooks().size() == 1
        transactionOperations.findTransactionStatus().isEmpty()

        when:
        bookService.saveAndChecked()

        then:
        thrown(Exception)
        bookService.listBooks().size() == 1
        transactionOperations.findTransactionStatus().isEmpty()

        when:
        bookService.saveAndManualRollback()

        then:
        bookService.listBooks().size() == 1
        transactionOperations.findTransactionStatus().isEmpty()
    }

    @Transactional
    static class BookService {
        @Inject EntityManager entityManager
        @Inject TransactionOperations<Session> transactionOperations

        List<Book> listBooks() {
            entityManager.createQuery("from Book").resultList
        }

        void saveAndError() {
            entityManager.persist(new Book(title: "The Stand", totalPages: 1000))
            throw new RuntimeException("Bad")
        }

        void saveAndChecked() {
            entityManager.persist(new Book(title: "The Shining", totalPages: 500))
            throw new Exception("Bad")
        }

        void saveAndManualRollback() {
            entityManager.persist(new Book(title: "Stuff", totalPages: 500))
            transactionOperations.findTransactionStatus().get().setRollbackOnly()
        }

        void saveAndSuccess() {
            entityManager.persist(new Book(title: "IT", totalPages: 1000))
        }
    }
}
