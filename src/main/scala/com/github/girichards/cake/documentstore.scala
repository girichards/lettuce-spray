
// http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth
// https://github.com/levkhomich/akka-tracing/wiki/Setup
// https://github.com/levkhomich/akka-tracing

sealed class Document {

}

trait DocumentStoreComponent {

  def documentStore: DocumentStore

  trait DocumentStore {
    def find(id: String): Document
    def store(document: Document)
  }

}

trait DocumentStoreJDBCComponent extends DocumentStoreComponent {

  trait DocumentStoreJDBC extends DocumentStore {
    def find(id: String): Document = { new Document() }
    def store(document: Document)
  }

}

trait DocumentStoreMongoDBComponent extends DocumentStoreComponent {

  trait DocumentStoreMongoDB extends DocumentStore {
    def find(id: String): Document = { new Document() }
    def store(document: Document)
  }

}

trait DocumentStoreCassandraComponent extends DocumentStoreComponent {

  trait DocumentStoreCassandra extends DocumentStore {
    def find(id: String): Document = { new Document() }
    def store(document: Document)
  }

}



trait DocumentServiceComponent {

  def documentService: DocumentService

  trait DocumentService {
    def find(id: String): Document
    def store(document: Document)
  }
}

trait DefaultDocumentServiceComponent extends DocumentServiceComponent {
  this: DocumentStoreComponent =>

  def documentService = new DefaultDocumentService

  class DefaultDocumentService extends DocumentService {
    def find(id: String) = documentStore.find(id)
    def store(document: Document) = documentStore.store(document)
  }

}





