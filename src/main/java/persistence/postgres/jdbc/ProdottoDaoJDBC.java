package persistence.postgres.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Prodotto;
import model.ProdottoAggregato;
import persistence.dao.ProdottoDao;

public class ProdottoDaoJDBC implements ProdottoDao {
	
	@Override
	public void save(Prodotto p) {
		String insert = "INSERT INTO prodotto(tipo,descrizione,prezzo,disponibile,img) VALUES (?,?,?,?,?)";		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(insert)) {
			
			PreparedStatement smt = handler.getStatement();
			
			//smt.setInt(1, p.getIdprodotto());
			smt.setString(1, p.getTipo());
			smt.setString(2, p.getDescrizione());
			smt.setInt(3, p.getPrezzo());
			smt.setBoolean(4, p.getDisponibile());
			smt.setString(5, p.getImg());


			
			handler.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}  
/*	
	@Override
	public List<Prodotto> retrieveBy(String column, Object value) {

		String query = "select * from Prodotto where ? = ?";
		List<Prodotto> prodotto = null;
		Prodotto temp = null;
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(query)) {

			handler.getStatement().setString(1, column);
			handler.getStatement().setObject(2, value);
			handler.executeQuery();
			
			if(handler.existsResultSet()) {
				prodotto = new ArrayList<Prodotto>();
				ResultSet result = handler.getResultSet();
				
				while(result.next()) {
					temp = new Prodotto();
					temp.setId(result.getInt("id"));				
					temp.setNome(result.getString("nome"));
					temp.setDescrizione(result.getString("descrizione"));
					temp.setPrezzo(result.getInt("prezzo"));
					temp.setImg(result.getString("img"));
					temp.setIdordine(result.getInt("idordine"));
					prodotto.add(temp);
				}
			}
			
			return prodotto;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
*/
	@Override
	public void prodottoCartRemove(Integer idprod) {
		String update = "UPDATE prodotto SET idordine = null  WHERE idprodotto = ?";
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(update)) {
	
			PreparedStatement smt = handler.getStatement();
			smt.setInt(1, idprod);	
			smt.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public void deletep(Integer id) {
		
		String delete = "DELETE FROM prenotazioni WHERE idprodotto = ? ";
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(delete)) {
			
			handler.getStatement().setInt(1,id);
			handler.getStatement().executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}	
	}
	
	@Override
	public List<Prodotto> retrieveByUserID(Integer ID) {

		String query = "SELECT p.* FROM Prodotto AS p, order AS o WHERE o.idClient == ? AND p.idOrdine== o.idOrder AND NOT o.pagato";
		List<Prodotto> books = null;
		Prodotto book = null;
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(query)) {
			
			handler.getStatement().setInt(1, ID);
			handler.executeQuery();
			
			if(handler.existsResultSet()) {
				books = new ArrayList<Prodotto>();
				ResultSet result = handler.getResultSet();
				while (result.next()) {
					book = new Prodotto();
					book.setIdprodotto(result.getInt("idprodotto"));				
					book.setTipo(result.getString("tipo"));
					book.setDescrizione(result.getString("descrizione"));
					book.setPrezzo(result.getInt("prezzo"));
					book.setDisponibile(result.getBoolean("disponibile"));
					book.setTipo(result.getString("img"));
					book.setIdordine(result.getInt("idordine"));
					books.add(book);
				}
			}
			
			return books;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public List<Prodotto> retrieveAll() {
					
			String query = "SELECT * FROM prodotto ";
			List<Prodotto> prodotti = null;
			Prodotto p = null;
			
			try(JDBCQueryHandler handler = new JDBCQueryHandler(query)) {
			
				handler.executeQuery();
				
				if(handler.existsResultSet()) {
					prodotti = new ArrayList<Prodotto>();
					ResultSet result = handler.getResultSet();
					
					while (result.next()) {
						p = new Prodotto();
						p.setIdprodotto(result.getInt("idprodotto"));		
						p.setTipo(result.getString("tipo"));
						p.setDescrizione(result.getString("descrizione"));
						p.setPrezzo(result.getInt("prezzo"));
						p.setDisponibile(result.getBoolean("disponibile"));
						p.setImg(result.getString("img"));
						p.setIdordine(result.getInt("idordine"));
						prodotti.add(p);
					}
				}
				
				return prodotti;
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	
	@Override
	public void connectByUserID(Integer id, Integer idProd) {
		
		String update = "UPDATE Prodotto AS p SET idordine = val SELECT idordine AS val FROM order AS o WHERE idProd = ? AND o.idOrder = p.idordine AND o.idClient = ?";
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(update)) {
			
			PreparedStatement smt = handler.getStatement();
			smt.setInt(1, id);
			smt.setInt(2, idProd);
			handler.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void update(Prodotto prodotto) {
		
		String update = "UPDATE prodotto SET tipo=?,descrizione=?,prezzo=? WHERE idprodotto=?";
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(update)) {
			
			PreparedStatement smt = handler.getStatement();
			
			smt.setString(1, prodotto.getTipo());
			smt.setString(2, prodotto.getDescrizione());
			smt.setInt(3, prodotto.getPrezzo());
			smt.setInt(4, prodotto.getIdprodotto());
			handler.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public void delete(Prodotto prodotto) {

		String delete = "delete FROM Prodotto WHERE idprodotto = ? ";
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(delete)) {
			
			handler.getStatement().setInt(1, prodotto.getIdprodotto());
			handler.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
/*
	@Override
	public ArrayList<Prodotto> retrieve(Integer nProd, Integer maxProd) {
		
		try(Connection connection = this.dataSource.getConnection()) {
		
			ArrayList<Prodotto> result = null;
			
			PreparedStatement maxpost = connection.prepareStatement("SELECT max(id) AS max FROM prodotto");
			ResultSet rsMax = maxpost.executeQuery();
			rsMax.next();
			Integer idMax = (int) rsMax.getLong("max");
			if ((maxProd + nProd) == idMax)
				return null;

			Integer from = idMax - maxProd; //maxPost + nPost - maxPost => nPost
			Integer to = from - nProd;      //nPost - nPost             => 0

			PreparedStatement create = connection.prepareStatement("SELECT * FROM prodotto WHERE id <= ? and id > ?");
			create.setInt(1, from);
			create.setInt(2, to);
			ResultSet rs;
			rs = create.executeQuery();
			boolean found = true;

			while (rs.next()) {
				if (found) {
					result = new ArrayList<>();
					found = false;
				}

				Prodotto prodotto = new Prodotto();
				
				prodotto.setId(rs.getInt("id"));				
				prodotto.setNome(rs.getString("nome"));
				prodotto.setDescrizione(rs.getString("descrizione"));
				prodotto.setPrezzo(rs.getInt("prezzo"));
				prodotto.setImg(rs.getString("img"));
				prodotto.setIdordine(rs.getInt("idordine"));
				
				result.add(prodotto);
			}

			if (result != null) {

				ArrayList<Prodotto> orderedPost = new ArrayList<Prodotto>();
				for (int i = result.size() - 1; i >= 0; i--) {
					orderedPost.add(result.get(i));
				}
				return orderedPost;
			}
			return null;
			
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}
*/
	@Override
	public Prodotto retrieve(Prodotto object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Prodotto> retrieveByType(String tipo) {
		String query = "SELECT tipo, descrizione, prezzo, disponibile, img,idprodotto FROM prodotto WHERE tipo = ?";
		Prodotto p = null;
		List<Prodotto> prodotti = null;
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(query)) {
			
			handler.getStatement().setString(1, tipo);
			handler.executeQuery();
			
			if(handler.existsResultSet()) {
				prodotti = new ArrayList<Prodotto>();
				ResultSet result = handler.getResultSet();

				while (result.next()) {
					p = new Prodotto();		
					p.setTipo(result.getString("tipo"));
					p.setDescrizione(result.getString("descrizione"));
					p.setPrezzo(result.getInt("prezzo"));
					p.setDisponibile(result.getBoolean("disponibile"));
					p.setImg(result.getString("img"));
					p.setIdprodotto(result.getInt("idprodotto"));
					if(prodotti.size()==0)
						prodotti.add(p);
				
				}
			}
			
			return prodotti;
			
		}  catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	

	@Override
	public List<ProdottoAggregato> showProductsForShop() {
		

		String query = "SELECT * FROM showproductsforshop where disponibile=true";

		List<ProdottoAggregato> prodotti = null;
		ProdottoAggregato p = null;
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(query)) {

			handler.executeQuery();
			
			if(handler.existsResultSet()) {
				prodotti = new ArrayList<ProdottoAggregato>();
				ResultSet result = handler.getResultSet();
				
				while (result.next()) {
					p = new ProdottoAggregato();		
					p.setTipo(result.getString("tipo"));
					p.setDescrizione(result.getString("descrizione"));
					p.setPrezzo(result.getInt("prezzo"));
					p.setDisponibile(result.getBoolean("disponibile"));
					p.setImg(result.getString("img"));
					p.setNumProdotti(result.getInt("num"));
					prodotti.add(p);
				}
			}
			
			return prodotti;

		} catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public List<ProdottoAggregato> showProductsForCart(Integer id) {

		String query = "SELECT  distinct pr.tipo, pr.descrizione, pv.totprezzo AS prezzo, pr.disponibile, pr.img, pv.num FROM productsbytypeorder AS pv, prodotto AS pr WHERE pv.idorder = 22 AND pr.tipo = pv.tipo AND pr.disponibile=false";
		List<ProdottoAggregato> prodotti = null;
		ProdottoAggregato p = null;
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(query)) {
			
			handler.getStatement().setInt(1, id);
			handler.executeQuery();
			
			if(handler.existsResultSet()) {
				prodotti = new ArrayList<ProdottoAggregato>();
				ResultSet result = handler.getResultSet();
				
				while (result.next()) {
					p = new ProdottoAggregato();		
					p.setTipo(result.getString("tipo"));
					p.setDescrizione(result.getString("descrizione"));
					p.setPrezzo(result.getInt("prezzo"));
					p.setDisponibile(result.getBoolean("disponibile"));
					p.setImg(result.getString("img"));
					p.setNumProdotti(result.getInt("num"));
					prodotti.add(p);
				}
			}
			
			return prodotti;
			
		}  catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	@Override
	public void updatesetordine(Integer idp,Integer ido) {
		
		String update = "update Prodotto set idordine=? where idprodotto=?";
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(update)) {
			
			PreparedStatement smt = handler.getStatement();
			
			smt.setInt(1,ido);
			smt.setInt(2,idp);
			handler.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	@Override
	public void updatesetdisponibile(Integer idp,Boolean val) {
		
		String update = "update Prodotto set disponibile=? where idprodotto=?";
		
		try(JDBCQueryHandler handler = new JDBCQueryHandler(update)) {
			
			PreparedStatement smt = handler.getStatement();
			smt.setBoolean(1,val);
			smt.setInt(2,idp);
			
			handler.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
