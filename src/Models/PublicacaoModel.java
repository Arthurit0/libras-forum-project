
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashSet;

public class PublicacaoModel {
    
    public static void create(PublicacaoBean p, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO publicacao (id_autor, texto, likes, deslikes, data_publicacao, removido, tipo_pub, id_regiao, id_pub, id_mencao, arquivo) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1, p.getId_autor());
            st.setString(2, p.getTexto());
            st.setInt(3, p.getLikes());
            st.setInt(4, p.getDeslikes());
            st.setDate(5, new Date(p.getDataPublicacao().getTime()));
            String tipoPub = p.getTipo_pub();
            st.setBoolean(6, p.isRemovido());
            st.setString(7, tipoPub);
            
        switch (tipoPub) {
            case "P":
                st.setInt(8, p.getId_regiao());
                st.setNull(9, Types.INTEGER);
                st.setNull(10, Types.INTEGER);
                st.setNull(11, Types.VARCHAR);
                break;
            case "R":
                st.setNull(8, Types.INTEGER);
                st.setInt(9, p.getId_pub());
                st.setNull(10, Types.INTEGER);
                st.setString(11, p.getArquivo());
                break;
            case "C":
                st.setNull(7, Types.INTEGER);
                st.setInt(8, p.getId_pub());
                st.setInt(9, p.getId_mencao());
                st.setNull(10, Types.VARCHAR);
                break;
            default:                
                st.setNull(8, Types.INTEGER);
                st.setNull(9, Types.INTEGER);
                st.setNull(10, Types.INTEGER);
                st.setNull(11, Types.VARCHAR);
                break;
        }
            st.execute();
            st.close();  
    }

    static HashSet<PublicacaoBean> listAll(Connection con) throws SQLException {
        Statement st;
        HashSet<PublicacaoBean> list = new HashSet<>();
            st = con.createStatement();
            String sql = "SELECT id, id_autor, texto, likes, deslikes, data_publicacao, removido, tipo_pub, id_regiao, id_pub, id_mencao, arquivo FROM publicacao ORDER BY id";
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                list.add(new PublicacaoBean(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4), res.getInt(5), new java.util.Date(res.getDate(6).getTime()), res.getBoolean(7), res.getString(8), res.getInt(9), res.getInt(10), res.getInt(11), res.getString(12)));
            }
        return list;
    }
    
    static HashSet<PublicacaoBean> listAllPerguntas(Connection con) throws SQLException {
        Statement st;
        HashSet<PublicacaoBean> list = new HashSet<>();
            st = con.createStatement();
            String sql = "SELECT id, id_autor, texto, likes, deslikes, data_publicacao, removido, tipo_pub, id_regiao, id_pub, id_mencao, arquivo FROM publicacao WHERE tipo_pub = 'P' ORDER BY id";
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                list.add(new PublicacaoBean(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4), res.getInt(5), new java.util.Date(res.getDate(6).getTime()), res.getBoolean(7), res.getString(8), res.getInt(9), res.getInt(10), res.getInt(11), res.getString(12)));
            }
        return list;
    }
    
    static HashSet<PublicacaoBean> listPublicacoesEAutores(Connection con) throws SQLException {
        Statement st;
        HashSet<PublicacaoBean> list = new HashSet<>();
            st = con.createStatement();
            String sql = "SELECT u.id, nome, email, u.id_regiao, avaliacao, cont_denuncias, is_adm, p.id, texto, likes, deslikes, data_publicacao, removido, tipo_pub, p.id_regiao, id_pub, id_mencao, arquivo FROM usuario u JOIN publicacao p ON u.id = p.id_autor ORDER BY p.id";
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                UsuarioBean user = new UsuarioBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getFloat(5), res.getInt(6), res.getBoolean(7));
                PublicacaoBean pub = new PublicacaoBean(res.getInt(8), res.getString(9), res.getInt(10), res.getInt(11), new java.util.Date(res.getDate(12).getTime()), res.getBoolean(13), res.getString(14), res.getInt(15), res.getInt(16), res.getInt(17), res.getString(18));
                pub.setUsuario(user);
                list.add(pub);
            }
        return list;
    }
    
    static HashSet<PublicacaoBean> listMaxPublicacaoRegiao(int id_usuario, Connection con) throws SQLException{
        Statement st;
        HashSet<PublicacaoBean> list = new HashSet<>();
            st = con.createStatement();
            String sql = "SELECT u.id, nome, email, u.id_regiao, avaliacao, cont_denuncias, is_adm, p.id, texto, likes, deslikes, data_publicacao, removido, tipo_pub, p.id_regiao, id_pub, id_mencao, arquivo FROM usuario u JOIN publicacao p ON p.id_autor = u.id AND u.id <> "+ id_usuario +" WHERE p.id_regiao = (SELECT id_regiao FROM usuario WHERE id = "+ id_usuario +") AND likes = (SELECT max(likes) FROM publicacao p JOIN usuario u ON p.id_autor = u.id AND u.id <> "+ id_usuario +") ORDER BY p.id;";
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                UsuarioBean user = new UsuarioBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getFloat(5), res.getInt(6), res.getBoolean(7));
                PublicacaoBean pub = new PublicacaoBean(res.getInt(8), res.getString(9), res.getInt(10), res.getInt(11), new Date(res.getDate(12).getTime()), res.getBoolean(13), res.getString(14), res.getInt(15), res.getInt(16), res.getInt(17), res.getString(18));
                pub.setUsuario(user);
                list.add(pub);
            }
        return list;
    }

}
